import etf.unsa.ba.nwt.recipe_service.model.CategoryDTO;
import etf.unsa.ba.nwt.recipe_service.model.PictureDTO;
import etf.unsa.ba.nwt.recipe_service.model.RecipeDTO;
import etf.unsa.ba.nwt.recipe_service.service.CategoryService;
import etf.unsa.ba.nwt.recipe_service.service.PictureService;
import etf.unsa.ba.nwt.recipe_service.service.RecipeService;
import etf.unsa.ba.nwt.recipe_service.service.StepService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = "classpath:./application-test.properties")
@SpringBootTest(classes={etf.unsa.ba.nwt.recipe_service.RecipeServiceApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
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

    private UUID pictureID;
    private UUID categoryID;
    private UUID userID;

    @BeforeEach
    public void beforeEachTest() {
        stepService.deleteAll();
        recipeService.deleteAll();
        categoryService.deleteAll();
        pictureService.deleteAll();

        pictureID=pictureService.create(new PictureDTO("Picture"));
        categoryID =categoryService.create(new CategoryDTO("Category" + pictureID, pictureID));
        userID = UUID.randomUUID();
    }
   @Test
    public void createRecipeSuccessTest() throws Exception{
        mockMvc.perform(post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"name\" : \"Test Recipe\",\n" +
                                "    \"description\":\"Test Description\",\n" +
                                "    \"preparationTime\":20, \n" +
                                "    \"userID\":\"%s\",\n" +
                                "    \"recipePicture\": \"%s\",\n" +
                                "    \"recipeCategory\": \"%s\"\n" +
                                "\n" +
                                "}", userID, pictureID, categoryID)))
                .andExpect(status().isCreated());
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
        UUID recipeID = recipeService.create(new RecipeDTO("TestName", "TestDescription", 20, userID,pictureID, categoryID));
        mockMvc.perform(delete(String.format("/api/recipes/%s", recipeID)))
                .andExpect(status().isOk());
    }

    @Test
    public void getRecipeByIdSuccess() throws Exception {
        UUID recipeID = recipeService.create(new RecipeDTO("TestName", "TestDescription", 20, userID,pictureID, categoryID));

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
        UUID recipeID = recipeService.create(new RecipeDTO("Test recipe name", "Test recipe description", 20, userID,pictureID, categoryID));
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
       UUID recipeID1 = recipeService.create(new RecipeDTO("Name", "Description...", 20, userID, pictureID, categoryID));
       UUID picture =pictureService.create(new PictureDTO("Picture"));
       UUID category =categoryService.create(new CategoryDTO("Category" + picture, picture));
       UUID recipeID2 = recipeService.create(new RecipeDTO("Name", "Description...", 20, userID, picture, category));

       mockMvc.perform(get(String.format("/api/recipes/users/%s", userID)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.*", hasSize(2)));
   }
   @Test
   public void getRecipesFromCategoryTest() throws Exception {
        UUID recipeID1 = recipeService.create(new RecipeDTO("Name1", "Description1...", 20, userID, pictureID, categoryID));
        UUID picture =pictureService.create(new PictureDTO("Picture"));
        UUID recipeID2 = recipeService.create(new RecipeDTO("Name2", "Description2...", 20, UUID.randomUUID(), picture, categoryID));
        UUID picture1 =pictureService.create(new PictureDTO("Picture"));
        UUID category1 =categoryService.create(new CategoryDTO("Category" + picture1, picture1));
        UUID recipeID3 = recipeService.create(new RecipeDTO("Name", "Description...", 20, userID, picture1, category1));

        mockMvc.perform(get(String.format("/api/recipes/categorys/%s", categoryID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }
   @AfterEach
   public void afterEachTest() {
   }
}
