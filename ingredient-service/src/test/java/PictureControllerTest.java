import ba.unsa.etf.nwt.ingredient_service.model.IngredientRecipeDTO;
import ba.unsa.etf.nwt.ingredient_service.model.PictureDTO;
import ba.unsa.etf.nwt.ingredient_service.service.PictureService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes={ba.unsa.etf.nwt.ingredient_service.IngredientServiceApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PictureControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PictureService pictureService;


    @Test
    public void createPictureSuccessTest() throws Exception{
        mockMvc.perform(post("/api/ingredientPictures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"picData\" : \"TestImage\"\n" +
                                "\n" +
                                "}")))
                .andExpect(status().isCreated());
    }

    @Test
    public void createPictureValidationsBlank() throws Exception {
        mockMvc.perform(post("/api/ingredientPictures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "\n" +
                                "}")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("Picture of ingredient is required")))
        ;
    }

    @Test
    public void deletePictureSuccess() throws Exception {
        UUID pictureID = pictureService.create(new PictureDTO("testPicture"));
        mockMvc.perform(delete(String.format("/api/ingredientPictures/%s", pictureID)))
                .andExpect(status().isOk());
    }

    @Test
    public void getPictureByIdSuccess() throws Exception {
        UUID pictureID = pictureService.create(new PictureDTO("testPicture"));
        mockMvc.perform(get(String.format("/api/ingredientPictures/%s", pictureID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(is(pictureID.toString())));
    }
    @Test
    public void getPictureByIdError() throws Exception {
        mockMvc.perform(get(String.format("/api/ingredientPictures/01011001-e012-1111-bd11-2c2a4faef0fc")))
                .andExpect(status().isNotFound());
    }

}


