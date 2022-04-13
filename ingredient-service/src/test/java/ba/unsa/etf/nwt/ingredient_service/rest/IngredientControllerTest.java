package ba.unsa.etf.nwt.ingredient_service.rest;

import ba.unsa.etf.nwt.ingredient_service.model.IngredientDTO;
import ba.unsa.etf.nwt.ingredient_service.model.PictureDTO;
import ba.unsa.etf.nwt.ingredient_service.service.IngredientService;
import ba.unsa.etf.nwt.ingredient_service.service.PictureService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@TestPropertySource(locations = "classpath:./application-test.properties")
@SpringBootTest(classes={ba.unsa.etf.nwt.ingredient_service.IngredientServiceApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IngredientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private IngredientService ingredientService;
    @Mock
    private DiscoveryClient discoveryClient;
    @Mock
    private RestTemplate restTemplate;
    private MockRestServiceServer mockServer;

    private UUID pictureID;
    private UUID recipeID;

    @BeforeEach
    public void beforeEachTest() {
        recipeID= UUID.fromString("0142b320-c67f-4d29-a58b-38a5cf6ec632");
        String resourceURL="http://localhost:8082";
        MockitoAnnotations.initMocks(this);
        ServiceInstance si = mock(ServiceInstance.class);
        when(si.getUri()).thenReturn(URI.create(resourceURL));
        doReturn(List.of(si)).when(discoveryClient).getInstances(anyString());
        ServiceInstance serviceInstanceRecipe = discoveryClient.getInstances("recipe-service").get(0);
        resourceURL = serviceInstanceRecipe.getUri() + "/api/recipes/";
        doReturn(new ResponseEntity<String>(HttpStatus.OK)).when(restTemplate).getForEntity(resourceURL+recipeID,String.class);
        MultipartFile file = null;
        try {
            file = new MockMultipartFile("image.jpg", new FileInputStream(new File("src/main/java/ba/unsa/etf/nwt/ingredient_service/image/image.jpg")));
            pictureID=pictureService.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ingredientService.setDiscoveryClient(discoveryClient);
        ingredientService.setRestTemplate(restTemplate);
    }
    @Test
    public void createIngredientSuccessTest() throws Exception{
        mockMvc.perform(post("/api/ingredients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"name\" : \"TestIngredient\",\n" +
                                "    \"calorieCount\":\"100\",\n" +
                                "    \"vitamins\":20, \n" +
                                "    \"carbohidrates\":30, \n" +
                                "    \"fat\":10, \n" +
                                "    \"proteins\":0, \n" +
                                "    \"measuringUnit\":\"gram\", \n" +
                                "    \"ingredientPicture\": \"%s\"\n" +
                                "\n" +
                                "}", pictureID)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createIngredientValidationsBlank() throws Exception {
        mockMvc.perform(post("/api/ingredients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"name\" : \"\",\n" +
                                "    \"calorieCount\":\"100\",\n" +
                                "    \"vitamins\":20, \n" +
                                "    \"carbohidrates\":30, \n" +
                                "    \"fat\":10, \n" +
                                "    \"proteins\":0, \n" +
                                "    \"measuringUnit\":\"gram\", \n" +
                                "    \"ingredientPicture\": \"%s\"\n" +
                                "\n" +
                                "}", pictureID)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("Ingredient name is required")))
        ;
    }

    @Test
    public void deleteIngredientSuccess() throws Exception {
        UUID ingredientID = ingredientService.create(new IngredientDTO("TestIngredient", 100, 20, 30, 10, 0, "gram",pictureID));
        mockMvc.perform(delete(String.format("/api/ingredients/%s", ingredientID)))
                .andExpect(status().isOk());
    }

    @Test
    public void getIngredientByIdSuccess() throws Exception {
        UUID ingredientID = ingredientService.create(new IngredientDTO("TestIngredient", 100, 20, 30, 10, 0, "gram",pictureID));
        mockMvc.perform(get(String.format("/api/ingredients/%s", ingredientID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(is(ingredientID.toString())));
    }
    @Test
    public void getIngredientByIdError() throws Exception {
        mockMvc.perform(get(String.format("/api/ingredients/11111111-1111-1111-1111-111111111111")))
                .andExpect(status().isNotFound());
    }
    
//    only after running recipe-service tests work
//    @Test
//    public void getTotalCaloriesSuccess() throws Exception {
//        mockMvc.perform(get("/ingredients/totalCalories/recipe").param("recipeId",recipeID.toString()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.totalCalories").value(isNotNull()));
//    }
//
//    @Test
//    public void getTotalCaloriesError() throws Exception {
//        mockMvc.perform(get("/ingredients/totalCalories/recipe").param("recipeId","11111111-1111-1111-1111-111111111111"))
//                .andExpect(status().isNotFound());
//    }

    @AfterEach
    public void afterEachTest() {
    }
}
