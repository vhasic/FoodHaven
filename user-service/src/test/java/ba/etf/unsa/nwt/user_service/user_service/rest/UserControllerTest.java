package ba.etf.unsa.nwt.user_service.user_service.rest;

import ba.etf.unsa.nwt.user_service.user_service.repos.RoleRepository;
import ba.etf.unsa.nwt.user_service.user_service.repos.UserRepository;
import ba.etf.unsa.nwt.user_service.user_service.service.RoleService;
import ba.etf.unsa.nwt.user_service.user_service.service.TokenService;
import ba.etf.unsa.nwt.user_service.user_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
/*    @Autowired
    private UserController userController;*/

    @Test
    public void contextLoads() {
    }
/*
    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }
*/

  /*  @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void getUser() {
    }

    @Test
    void createUser() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"firstName\":\"User\",\n" +
                                "    \"lastName\":\"User\",\n" +
                                "    \"username\":\"user2\",\n" +
                                "    \"email\":\"user2@nesto.com\",\n" +
                                "    \"password\":\"Password1!\"\n" +
                                "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }*/
}