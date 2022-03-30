package ba.unsa.etf.nwt.ingredient_service.rest;

import ba.unsa.etf.nwt.ingredient_service.model.IngredientDTO;
import ba.unsa.etf.nwt.ingredient_service.model.PictureDTO;
import ba.unsa.etf.nwt.ingredient_service.service.IngredientService;
import ba.unsa.etf.nwt.ingredient_service.service.PictureService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@TestPropertySource(locations = "classpath:./application-test.properties")
@SpringBootTest(classes={ba.unsa.etf.nwt.ingredient_service.IngredientServiceApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class IngredientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private IngredientService ingredientService;

    private UUID pictureID;

    @BeforeEach
    public void beforeEachTest() {
        MultipartFile file = null;
        try {
            file = new MockMultipartFile("image.jpg", new FileInputStream(new File("src/main/java/ba/unsa/etf/nwt/ingredient_service/image/image.jpg")));
            pictureID=pictureService.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        mockMvc.perform(get(String.format("/api/ingredients/01011001-e012-1111-bd11-2c2a4faef0fc")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getTotalCaloriesSuccess() throws Exception {
        mockMvc.perform(get(String.format("/api/ingredients/totalCalories/0826a497-202c-4cfb-9191-b474f3e3c8df")))
                .andExpect(status().isOk());
    }

    @Test
    public void getTotalCaloriesError() throws Exception {
        mockMvc.perform(get(String.format("/api/ingredients/totalCalories/01011001-e012-1111-bd11-2c2a4faef0fc")))
                .andExpect(jsonPath("$").doesNotExist());
    }

    @AfterEach
    public void afterEachTest() {
    }
}