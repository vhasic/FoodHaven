package ba.unsa.etf.nwt.ingredient_service.rest;

import ba.unsa.etf.nwt.ingredient_service.service.PictureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = "classpath:./application-test.properties")
@SpringBootTest(classes={ba.unsa.etf.nwt.ingredient_service.IngredientServiceApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PictureControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PictureService pictureService;

    @Test
    public void uploadPictureSuccessTest() throws Exception{
        File file = new File("src/main/java/ba/unsa/etf/nwt/ingredient_service/image/image.jpg");
        FileInputStream fis = new FileInputStream(file);
        MockMultipartFile multipart = new MockMultipartFile(
                "file", file.getName(), "multipart/form-data",
                fis);
        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/api/ingredientPictures/upload").file(multipart))
                .andExpect(status().isCreated());
    }

    @Test
    public void deletePictureSuccess() throws Exception {
        UUID pictureID = null;
        MultipartFile file = null;
        try {
            file = new MockMultipartFile("image.jpg", new FileInputStream(new File("src/main/java/ba/unsa/etf/nwt/ingredient_service/image/image.jpg")));
            pictureID=pictureService.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mockMvc.perform(delete(String.format("/api/ingredientPictures/%s", pictureID)))
                .andExpect(status().isOk());
    }

    @Test
    public void getPictureByIdSuccess() throws Exception {
        UUID pictureID = null;
        MultipartFile file = null;
        try {
            file = new MockMultipartFile("image.jpg", new FileInputStream(new File("src/main/java/ba/unsa/etf/nwt/ingredient_service/image/image.jpg")));
            pictureID=pictureService.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mockMvc.perform(get(String.format("/api/ingredientPictures/%s", pictureID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(is(pictureID.toString())));
    }
    @Test
    public void getPictureByIdError() throws Exception {
        mockMvc.perform(get(String.format("/api/ingredientPictures/11111111-1111-1111-1111-111111111111")))
                .andExpect(status().isNotFound());
    }

}
