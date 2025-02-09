package ba.etf.unsa.nwt.rating_service.rest;

import ba.etf.unsa.nwt.rating_service.model.RatingDTO;
import ba.etf.unsa.nwt.rating_service.service.RatingService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RatingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RatingService ratingService;
    private UUID uuid1,uuid2,ratingId1,ratingId2,ratingId3;
    @Mock
    private DiscoveryClient discoveryClient;
    @Mock
    private RestTemplate restTemplate;


    @BeforeAll
    public void beforeAll() {
        uuid1= UUID.fromString("21a7dc09-8e5c-4920-b02d-b3c63e1bf9c3");
        uuid2= UUID.fromString("2879d230-5692-4b1b-8443-868c5cca152c");
        String resourceURL="http://localhost:8082";
        MockitoAnnotations.initMocks(this);
        ServiceInstance si = mock(ServiceInstance.class);
        when(si.getUri()).thenReturn(URI.create(resourceURL));
        doReturn(List.of(si)).when(discoveryClient).getInstances(anyString());
        ServiceInstance serviceInstanceRecipe = discoveryClient.getInstances("recipe-service").get(0);
        resourceURL = serviceInstanceRecipe.getUri() + "/api/recipes/";
        doReturn(new ResponseEntity<String>(HttpStatus.OK)).when(restTemplate).getForEntity(resourceURL+uuid1,String.class);
        doReturn(new ResponseEntity<String>(HttpStatus.OK)).when(restTemplate).getForEntity(resourceURL+uuid2,String.class);
        ratingService.setDiscoveryClient(discoveryClient);
        ratingService.setRestTemplate(restTemplate);
        ratingId1= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",uuid1,uuid2));
        ratingId2= ratingService.create(new RatingDTO(4,"Ovo je testni komentar 2",uuid1,uuid1));
        ratingId3= ratingService.create(new RatingDTO(3,"Ovo je testni komentar 3",uuid1,uuid1));
    }

    @Test
    void getAllRatings() throws Exception {
        mockMvc.perform(get("/api/ratings"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }


    @Test
    public void createRatingValidation() throws Exception{
        mockMvc.perform(post("/api/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"rating\":10,\n" +
                                "    \"comment\":\"I followed this to the letter, except I substituted walnuts and tofu for the skirt steak, ditched the cheese entirely, and replaced the starch with a turnip salad. Turned out great. My seven-year-old boys have never seen a dessert and I’ve convinced them that walnut-and-turnip salad is “cake.” Thanks for the recipe!.\",\n" +
                                "    \"recipeId\":\"%s\",\n" +
                                "    \"userId\":\"%s\"\n" +
                                "}", uuid1, uuid2)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("Rating should be between 1-5", "Comment can't be longer than 255 characters")))
        ;
    }
    @Test
    public void createRatingValidationBlank() throws Exception{
        mockMvc.perform(post("/api/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"recipeId\":\"%s\",\n" +
                                "    \"userId\":\"%s\"\n" +
                                "}", uuid1, uuid2)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("Comment can't be null", "Rating can't be null")))
        ;
    }

    @Test
    public void deleteRatingSuccess() throws Exception {
        UUID id= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",uuid1, uuid2));
        mockMvc.perform(delete(String.format("/api/ratings/%s", id)))
                .andExpect(status().isOk());
    }

    @Test
    public void getRatingByIdSuccess() throws Exception {
        UUID id= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",uuid1, uuid2));
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
        UUID id= ratingService.create(new RatingDTO(2,"Ovo je testni komentar",uuid1, uuid2));
        mockMvc.perform(put(String.format("/api/ratings/%s", id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"rating\":3,\n" +
                                "    \"comment\":\"Ažurirani testni komentar.\",\n" +
                                "    \"recipeId\":\"%s\",\n" +
                                "    \"userId\":\"%s\"\n" +
                                "}", uuid1, uuid2)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllRatingsForUser() throws Exception {
        mockMvc.perform(get("/api/ratings/user").param("userId",uuid1.toString()))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void getAllRatingsForRecipe() throws Exception {
        mockMvc.perform(get("/api/ratings/recipe").param("recipeId",uuid1.toString()))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void getAverageRatingForRecipe() throws Exception {
        mockMvc.perform(get("/api/ratings/averageRating").param("recipeId",uuid1.toString()))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.averageRating").value(4)
                );
    }

    @Test
    public void createRatingSuccessTest() throws Exception{
        JSONObject json = new JSONObject();
        json.put("rating", 5);
        json.put("comment", "Test");
        json.put("recipeId", uuid1.toString());
        json.put("userId", uuid2.toString());
        mockMvc.perform(post("/api/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString()))
                .andExpect(status().isCreated());
    }

    @Test
    public void createRatingNotFound() throws Exception{
        mockMvc.perform(post("/api/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\n" +
                                "    \"rating\":3,\n" +
                                "    \"comment\":\"Test\",\n" +
                                "    \"recipeId\":\"%s\",\n" +
                                "    \"userId\":\"%s\"\n" +
                                "}", UUID.randomUUID(), UUID.randomUUID())))
                .andExpect(status().isNotFound());
    }

    @AfterEach
    public void afterEachTest() {
    }

}