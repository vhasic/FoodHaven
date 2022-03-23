package ba.etf.unsa.nwt.user_service.user_service.rest;

import ba.etf.unsa.nwt.user_service.user_service.AppConfig;
import ba.etf.unsa.nwt.user_service.user_service.UserServiceApplication;
import ba.etf.unsa.nwt.user_service.user_service.model.RoleDTO;
import ba.etf.unsa.nwt.user_service.user_service.model.UserDTO;
import ba.etf.unsa.nwt.user_service.user_service.repos.RoleRepository;
import ba.etf.unsa.nwt.user_service.user_service.repos.UserRepository;
import ba.etf.unsa.nwt.user_service.user_service.service.RoleService;
import ba.etf.unsa.nwt.user_service.user_service.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@ActiveProfiles("test")
//@TestPropertySource(locations = "classpath:./application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {UserServiceApplication.class, AppConfig.class})
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    UUID adminRoleId, userRoleId, adminId;

    @BeforeAll
    public void setUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        adminRoleId =roleService.create(new RoleDTO("Administrator"));
        userRoleId =roleService.create(new RoleDTO("User"));
        // save a few users
        adminId =userService.create(new UserDTO("Administrator","Administrator","admin","admin@nesto.com","Password1!", adminRoleId));
//        userService.create(new UserDTO("User","User","user","user@nesto.com","Password1!",userId));
    }

    @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void getUserSuccess() throws Exception {
        UUID id=userService.create(new UserDTO("User","User","user","user@nesto.com","Password1!",userRoleId));
        mockMvc.perform(get(String.format("/api/users/%s", id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(is(id.toString())));
    }

    @Test
    void getUserError() throws Exception {
        mockMvc.perform(get("/api/users/01011001-e012-1111-bd11-2c2a4faef0fc"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUserSuccess() throws Exception {
        mockMvc.perform(post("/api/users")
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
    void createUserValidationTooLongFirstName() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"firstName\":\"aaaaaaaaaaaaaaaaaaaaa\",\n" +
                                "    \"lastName\":\"aa\",\n" +
                                "    \"username\":\"a\",\n" +
                                "    \"email\":\"a@nesto.com\",\n" +
                                "    \"password\":\"Password1!\"\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field",containsInAnyOrder("First name can't be longer than twenty characters")));
    }

    @Test
    void createUserValidationTooLongLastName() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"firstName\":\"aa\",\n" +
                                "    \"lastName\":\"aaaaaaaaaaaaaaaaaaaaa\",\n" +
                                "    \"username\":\"a\",\n" +
                                "    \"email\":\"a@nesto.com\",\n" +
                                "    \"password\":\"Password1!\"\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field",containsInAnyOrder("Last name can't be longer than twenty characters")));
    }

    @Test
    void createUserValidationPassword() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"firstName\":\"aa\",\n" +
                                "    \"lastName\":\"a\",\n" +
                                "    \"username\":\"a\",\n" +
                                "    \"email\":\"a@nesto.com\",\n" +
                                "    \"password\":\"Password\"\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field",containsInAnyOrder("Password must contain at least one lowercase, one uppercase, one digit and one special character")));
    }

    @Test
    void createUserBlankUsername() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"firstName\":\"aa\",\n" +
                                "    \"lastName\":\"a\",\n" +
                                "    \"username\":\"\",\n" +
                                "    \"email\":\"a@nesto.com\",\n" +
                                "    \"password\":\"Password1!\"\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field",containsInAnyOrder("Username can't be blank")));
    }

    @Test
    void updateUser() throws Exception {
        mockMvc.perform(put(String.format("/api/users/%s", adminId))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\":\"Administrator\",\n" +
                        "    \"lastName\":\"Administrator\",\n" +
                        "    \"username\":\"admin\",\n" +
                        "    \"email\":\"admin@nesto.com\",\n" +
                        "    \"password\":\"Password2!\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserSuccess() throws Exception {
        UUID id=userService.create(new UserDTO("User","User","user3","user3@nesto.com","Password1!",userRoleId));
        mockMvc.perform(delete(String.format("/api/users/%s", id)))
                .andExpect(status().isOk());
    }
}