import etf.unsa.ba.nwt.recipe_service.model.CategoryDTO;
import etf.unsa.ba.nwt.recipe_service.model.RecipeDTO;
import etf.unsa.ba.nwt.recipe_service.model.StepDTO;
import etf.unsa.ba.nwt.recipe_service.repos.CategoryRepository;
import etf.unsa.ba.nwt.recipe_service.repos.PictureRepository;
import etf.unsa.ba.nwt.recipe_service.repos.RecipeRepository;
import etf.unsa.ba.nwt.recipe_service.service.CategoryService;
import etf.unsa.ba.nwt.recipe_service.service.PictureService;
import etf.unsa.ba.nwt.recipe_service.service.RecipeService;
import etf.unsa.ba.nwt.recipe_service.service.StepService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = "classpath:./application-test.properties")
@SpringBootTest(classes={etf.unsa.ba.nwt.recipe_service.RecipeServiceApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StepControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private StepService stepService;
    @Mock
    private DiscoveryClient discoveryClient;
    @Mock
    private RestTemplate restTemplate;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private UUID pictureID;
    private UUID categoryID;
    private UUID userID;
    private UUID recipeID;
    private UUID pictureID1;

    @BeforeEach
    public void beforeEach() {
        stepService.deleteAll();
        recipeService.deleteAll();
        categoryService.deleteAll();
        pictureService.deleteAll();

        String resourceURL="http://localhost:8083";
        MockitoAnnotations.initMocks(this);
        ServiceInstance si = mock(ServiceInstance.class);
        when(si.getUri()).thenReturn(URI.create(resourceURL));
        doReturn(List.of(si)).when(discoveryClient).getInstances(anyString());
        userID= UUID.fromString("9374c7fa-9b39-42d0-af4a-b4f93701cd9a");

        ServiceInstance serviceInstanceRecipe = discoveryClient.getInstances("user-service").get(0);
        resourceURL = serviceInstanceRecipe.getUri() + "/api/users/";
        doReturn(new ResponseEntity<String>(HttpStatus.OK)).when(restTemplate).getForEntity(resourceURL+userID,String.class);
        recipeService=new RecipeService(recipeRepository,restTemplate,discoveryClient, pictureRepository, categoryRepository);
        try {
            File file = new File("src/main/java/etf/unsa/ba/nwt/recipe_service/image/image.jpg");
            FileInputStream fis = new FileInputStream(file);
            MockMultipartFile multipart = new MockMultipartFile("file", file.getName(), "image/jpeg", fis);
            pictureID=pictureService.create(multipart);
        } catch (IOException e) {
            e.printStackTrace();
        }
        categoryID =categoryService.create(new CategoryDTO("Category" + pictureID, pictureID));
        recipeID = recipeService.create(new RecipeDTO("TestName", "TestDescription", 20, userID,pictureID, categoryID));

    }

   @Test
    public void createCategorySuccessTest() throws Exception{
        mockMvc.perform(post("/api/steps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"description\": \"Straining the custard filters out any eggy bits to ensure a nice, smooth consistency. Carefully pour the custard through a wire strainer (known as a sieve)\",\n" +
                                "    \"stepPicture\": \"%s\",\n" +
                                "    \"stepRecipe\": \"%s\",\n" +
                                "    \"onumber\": 1}", pictureID, recipeID)))
                .andExpect(status().isCreated());
    }
    @Test
    public void updateCategoryTest() throws Exception{
        UUID stepID = stepService.create(new StepDTO("New description",1, pictureID, recipeID));
        mockMvc.perform(put(String.format("/api/steps/%s", stepID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"description\": \"Updated description\",\n" +
                                "    \"stepPicture\": \"%s\",\n" +
                                "    \"stepRecipe\": \"%s\",\n" +
                                "    \"onumber\": 1}", pictureID, recipeID)))
                .andExpect(status().isOk());
    }
    @Test
    public void createRecipeValidationBlank() throws Exception{
        mockMvc.perform(post("/api/steps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"description\": \"\",\n" +
                                "    \"stepPicture\": \"%s\",\n" +
                                "    \"stepRecipe\": \"%s\",\n" +
                                "    \"onumber\": 1}", pictureID, recipeID)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("Step description may not be blank!")))
        ;
    }



    @Test
    public void createRecipeValidationTooLong() throws Exception{
        mockMvc.perform(post("/api/steps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"description\": \"Straining the custard filters out any eggy bits to ensure a nice, smooth consistency. Carefully pour the custard through a wire strainer (known as a sieve). Here's a tip: rinse any egg particles out of the sieve with cold water before washing it. Hot water will cook the egg particles into the wire mesh, making it really difficult to clean.\",\n" +
                                "    \"stepPicture\": \"%s\",\n" +
                                "    \"stepRecipe\": \"%s\",\n" +
                                "    \"onumber\": 1}", pictureID, recipeID)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("Step description can't be longer than 255 characters!")))
        ;
    }

    @Test
    public void deleteStepSuccess() throws Exception {
        UUID stepID = stepService.create(new StepDTO("In saucepan, combine milk and whipping cream",1, pictureID, recipeID));
        mockMvc.perform(delete(String.format("/api/steps/%s", stepID)))
                .andExpect(status().isOk());
    }

    @Test
    public void getStepByIdSuccess() throws Exception {
        UUID stepID = stepService.create(new StepDTO("Test Description...",1, pictureID, recipeID));

        mockMvc.perform(get(String.format("/api/steps/%s", stepID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(is(stepID.toString())));
    }
    @Test
    public void getStepByIdError() throws Exception {
        mockMvc.perform(get(String.format("/api/steps/01011001-e012-1111-bd11-2c2a4faef0fc")))
                .andExpect(status().isNotFound());
    }
    @Test
    public void deleteAll() throws Exception {
        mockMvc.perform(delete(String.format("/api/steps")))
                .andExpect(status().isOk());
    }
    @Test
    public void getStepsForRecipeTest() throws Exception {
        stepService.create(new StepDTO("Step Description 1...", 1, pictureID, recipeID));
        try {
            File file = new File("src/main/java/etf/unsa/ba/nwt/recipe_service/image/image.jpg");
            FileInputStream fis = new FileInputStream(file);
            MockMultipartFile multipart = new MockMultipartFile("file", file.getName(), "image/jpeg", fis);
            pictureID1 = pictureService.create(multipart);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stepService.create(new StepDTO("Step Description 2...", 2, pictureID1, recipeID));

        mockMvc.perform(get(String.format("/api/steps/recipe?recipeId=%s", recipeID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }
    @Test
    public void getStepXForRecipeTest() throws Exception {
        stepService.create(new StepDTO("Step Description 1...",7, pictureID, recipeID));

        mockMvc.perform(get(String.format("/api/steps/number?recipeId=%s&number=%s", recipeID, 7)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(is("Step Description 1...")));
    }
    @AfterEach
    public void afterEachTest() {
    }

}
