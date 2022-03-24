package ba.etf.unsa.nwt.rating_service.rest;

import ba.etf.unsa.nwt.rating_service.model.RatingDTO;
import ba.etf.unsa.nwt.rating_service.service.RatingService;
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

import java.util.UUID;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class RatingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RatingService ratingService;

    @BeforeEach
    public void beforeEachTest() {

    }
    @Test
    public void createRatingSuccessTest() throws Exception{
        UUID recipeID = UUID.randomUUID();
        UUID userID = UUID.randomUUID();
        mockMvc.perform(post("/api/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"rating\":1,\n" +
                                "    \"comment\":\"Ovo je testni komentar.\",\n" +
                                "    \"recipeId\":\"%s\",\n" +
                                "    \"userId\":\"%s\"\n" +
                                "}", recipeID, userID)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createRatingValidation() throws Exception{
        UUID recipeID = UUID.randomUUID();
        UUID userID = UUID.randomUUID();
        mockMvc.perform(post("/api/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"rating\":10,\n" +
                                "    \"comment\":\"I followed this to the letter, except I substituted walnuts and tofu for the skirt steak, ditched the cheese entirely, and replaced the starch with a turnip salad. Turned out great. My seven-year-old boys have never seen a dessert and I’ve convinced them that walnut-and-turnip salad is “cake.” Thanks for the recipe!.\",\n" +
                                "    \"recipeId\":\"%s\",\n" +
                                "    \"userId\":\"%s\"\n" +
                                "}", recipeID, userID)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("Rating should be between 1-5", "Comment can't be longer than 255 characters")))
        ;
    }
    @Test
    public void createRatingValidationBlank() throws Exception{
        UUID recipeID = UUID.randomUUID();
        UUID userID = UUID.randomUUID();
        mockMvc.perform(post("/api/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"recipeId\":\"%s\",\n" +
                                "    \"userId\":\"%s\"\n" +
                                "}", recipeID, userID)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("Comment can't be null", "Rating can't be null")))
        ;
    }

    @Test
    public void deleteRatingSuccess() throws Exception {
        UUID id= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",UUID.randomUUID(), UUID.randomUUID()));
        mockMvc.perform(delete(String.format("/api/ratings/%s", id)))
                .andExpect(status().isOk());
    }

    @Test
    public void getRatingByIdSuccess() throws Exception {
        UUID id= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",UUID.randomUUID(), UUID.randomUUID()));
        mockMvc.perform(get(String.format("/api/ratings/%s", id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(is(id.toString())));
    }
    @Test
    public void getRatingByIdError() throws Exception {
        mockMvc.perform(get(String.format("/api/ratings/01011001-e012-1111-bd11-0c0a0faef00c")))
                .andExpect(status().isNotFound());
    }
    @Test
    void updateRating() throws Exception {
        UUID recipeID = UUID.randomUUID();
        UUID userID = UUID.randomUUID();
        UUID id= ratingService.create(new RatingDTO(2,"Ovo je testni komentar",recipeID, userID));
        mockMvc.perform(put(String.format("/api/ratings/%s", id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"rating\":3,\n" +
                                "    \"comment\":\"Ažurirani testni komentar.\",\n" +
                                "    \"recipeId\":\"%s\",\n" +
                                "    \"userId\":\"%s\"\n" +
                                "}", recipeID, userID)))
                .andExpect(status().isOk());
    }

    @AfterEach
    public void afterEachTest() {
    }

}