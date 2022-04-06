package ba.unsa.etf.nwt.ingredient_service.rest;

import ba.unsa.etf.nwt.ingredient_service.model.IngredientDTO;
import ba.unsa.etf.nwt.ingredient_service.model.IngredientRecipeDTO;
import ba.unsa.etf.nwt.ingredient_service.model.PictureDTO;
import ba.unsa.etf.nwt.ingredient_service.service.IngredientRecipeService;
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
public class IngredientRecipeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private IngredientRecipeService ingredientRecipeService;

    private UUID pictureID;
    private UUID ingredientID;
    private UUID uuid;

    @BeforeEach
    public void beforeEachTest() {
        uuid= UUID.randomUUID();
        MultipartFile file = null;
        try {
            file = new MockMultipartFile("image.jpg", new FileInputStream(new File("src/main/java/ba/unsa/etf/nwt/ingredient_service/image/image.jpg")));
            pictureID=pictureService.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ingredientID=ingredientService.create(new IngredientDTO("Test", 10,
                1, 2, 3, 4, "test", pictureID));
    }
    @Test
    public void createIngredientRecipeSuccessTest() throws Exception{
        mockMvc.perform(post("/api/ingredientRecipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"quantity\" : \"100\",\n" +
                                "    \"recipeID\": \"%s\",\n" +
                                "    \"ingredientRecipeID\": \"%s\"\n" +
                                "\n" +
                                "}", uuid, ingredientID)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createIngredientRecipeValidationsBlank() throws Exception {
        mockMvc.perform(post("/api/ingredientRecipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"quantity\" : \"\",\n" +
                                "    \"recipeID\": \"%s\",\n" +
                                "    \"ingredientRecipeID\": \"%s\"\n" +
                                "\n" +
                                "}", uuid, ingredientID)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("Quantity of ingredient is required")))
        ;
    }

    @Test
    public void deleteIngredientRecipeSuccess() throws Exception {
        UUID ingredientRecipeID = ingredientRecipeService.create(new IngredientRecipeDTO(1, uuid, ingredientID));
        mockMvc.perform(delete(String.format("/api/ingredientRecipes/%s", ingredientRecipeID)))
                .andExpect(status().isOk());
    }

    @Test
    public void getIngredientRecipeByIdSuccess() throws Exception {
        UUID ingredientRecipeID = ingredientRecipeService.create(new IngredientRecipeDTO(1, uuid, ingredientID));
        mockMvc.perform(get(String.format("/api/ingredientRecipes/%s", ingredientRecipeID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(is(ingredientRecipeID.toString())));
    }
    @Test
    public void getIngredientRecipeByIdError() throws Exception {
        mockMvc.perform(get(String.format("/api/ingredientRecipes/11111111-1111-1111-1111-111111111111")))
                .andExpect(status().isNotFound());
    }

    @AfterEach
    public void afterEachTest() {
    }
}