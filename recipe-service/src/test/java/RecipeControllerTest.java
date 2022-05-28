import etf.unsa.ba.nwt.recipe_service.model.CategoryDTO;
import etf.unsa.ba.nwt.recipe_service.model.RecipeDTO;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = "classpath:./application-test.properties")
@SpringBootTest(classes={etf.unsa.ba.nwt.recipe_service.RecipeServiceApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecipeControllerTest {
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
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Mock
    private DiscoveryClient discoveryClient;
    @Mock
    private RestTemplate restTemplate;
    private UUID pictureID;
    private UUID categoryID;
    private UUID userID;
    private UUID recipeID;


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
    public void createRecipeValidationsTooLong() throws Exception {
        mockMvc.perform(post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"name\" : \"Pâté of roasted indigenous legumes, paired with a compote of seasonal berries, served on hearty sprouted wheat bread.\",\n" +
                                "    \"description\":\"Here is the archetype of a thin-crust pizza pie, a pizza margherita adorned simply in the colors of the Italian flag: green from basil, white from mozzarella, red from tomato sauce.The most ordinary pizzas, called coll'aglio e l'olio (with garlic and oil), are dressed with oil, and over there it's spread, as well as salt, the origanum and garlic cloves shredded minutely. Others are covered with grated cheese and dressed with lard, and then they put over a few leaves of basil. Over the firsts is often added some small seafish; on the seconds some thin slices of mozzarella. Sometimes they use slices of prosciutto, tomato, arselle, etc.... Sometimes folding the dough over itself it forms what is called calzone.The most ordinary pizzas, called coll'aglio e l'olio (with garlic and oil), are dressed with oil, and over there it's spread, as well as salt, the origanum and garlic cloves shredded minutely. Others are covered with grated cheese and dressed with lard, and then they put over a few leaves of basil. Over the firsts is often added some small seafish; on the seconds some thin slices of mozzarella. Sometimes they use slices of prosciutto, tomato, arselle, etc.... Sometimes folding the dough over itself it forms what is called calzone.The most ordinary pizzas, called coll'aglio e l'olio (with garlic and oil), are dressed with oil, and over there it's spread, as well as salt, the origanum and garlic cloves shredded minutely. Others are covered with grated cheese and dressed with lard, and then they put over a few leaves of basil. Over the firsts is often added some small seafish; on the seconds some thin slices of mozzarella. Sometimes they use slices of prosciutto, tomato, arselle, etc.... Sometimes folding the dough over itself it forms what is called calzone.The most ordinary pizzas, called coll'aglio e l'olio (with garlic and oil), are dressed with oil, and over there it's spread, as well as salt, the origanum and garlic cloves shredded minutely. Others are covered with grated cheese and dressed with lard, and then they put over a few leaves of basil. Over the firsts is often added some small seafish; on the seconds some thin slices of mozzarella. Sometimes they use slices of prosciutto, tomato, arselle, etc.... Sometimes folding the dough over itself it forms what is called calzone.\",\n" +
                                "    \"preparationTime\":20, \n" +
                                "    \"userID\":\"%s\",\n" +
                                "    \"recipePicture\": \"%s\",\n" +
                                "    \"recipeCategory\": \"%s\"\n" +
                                "\n" +
                                "}", userID, pictureID, categoryID)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("Recipe name can't be longer than 50 characters!", "Recipe description can't be longer than 255 characters!")))
        ;
    }
    @Test
    public void createRecipeValidationsBlank() throws Exception {
        mockMvc.perform(post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"name\" : \"\",\n" +
                                "    \"description\":\"Here is the archetype of a thin-crust pizza pie....\",\n" +
                                "    \"preparationTime\":20, \n" +
                                "    \"userID\":\"%s\",\n" +
                                "    \"recipePicture\": \"%s\",\n" +
                                "    \"recipeCategory\": \"%s\"\n" +
                                "\n" +
                                "}", userID, pictureID, categoryID)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("Recipe name is required!")))
        ;
    }

    @Test
    public void deleteRecipeSuccess() throws Exception {
        mockMvc.perform(delete(String.format("/api/recipes/%s", recipeID)))
                .andExpect(status().isOk());
    }

    @Test
    public void getRecipeByIdSuccess() throws Exception {
        //UUID recipeID = recipeService.create(new RecipeDTO("TestName", "TestDescription", 20, userID,pictureID, categoryID));

        mockMvc.perform(get(String.format("/api/recipes/%s", recipeID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(is(recipeID.toString())));
    }
    @Test
    public void getRecipeByIdError() throws Exception {
        mockMvc.perform(get(String.format("/api/recipes/01011001-e012-1111-bd11-2c2a4faef0fc")))
                .andExpect(status().isNotFound());
    }
    @Test
    void updateRecipeTest() throws Exception {
        //UUID recipeID = recipeService.create(new RecipeDTO("Test recipe name", "Test recipe description", 20, userID,pictureID, categoryID));
        mockMvc.perform(put(String.format("/api/recipes/%s", recipeID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"name\" : \"Updated test recipe name\",\n" +
                                "    \"description\":\"Updated test recipe description...\",\n" +
                                "    \"preparationTime\":20, \n" +
                                "    \"userID\":\"%s\",\n" +
                                "    \"recipePicture\": \"%s\",\n" +
                                "    \"recipeCategory\": \"%s\"\n" +
                                "\n" +
                                "}", userID, pictureID, categoryID)))
                .andExpect(status().isOk());
    }
   @Test
   public void getRecipesFromUserTest() throws Exception {
       mockMvc.perform(get(String.format("/api/recipes/user?userId=%s", userID)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.*", hasSize(1)));
   }
   @Test
   public void getRecipesFromCategoryTest() throws Exception {
        mockMvc.perform(get(String.format("/api/recipes/category?categoryId=%s", categoryID)))
                .andExpect(status().isOk());
    }
   @AfterEach
   public void afterEachTest() {
   }
}
