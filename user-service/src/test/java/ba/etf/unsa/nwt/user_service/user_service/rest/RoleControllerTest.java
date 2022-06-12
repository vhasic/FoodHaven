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
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource(locations = "classpath:./application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {UserServiceApplication.class, AppConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RoleControllerTest {
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

    UUID adminRoleId, userRoleId;

    @BeforeAll
    public void setUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        adminRoleId =roleService.create(new RoleDTO("Administrator"));
        userRoleId =roleService.create(new RoleDTO("User"));
    }

    @Test
    void getAllRoles() throws Exception {
        mockMvc.perform(get("/api/roles"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void getRoleSuccess() throws Exception {
        mockMvc.perform(get(String.format("/api/roles/%s", userRoleId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(is(userRoleId.toString())));
    }

    @Test
    void createRoleSuccess() throws Exception {
        mockMvc.perform(post("/api/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"Chef\"\n" +
                                "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void createRoleValidationError() throws Exception {
        mockMvc.perform(post("/api/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"\"\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field",containsInAnyOrder("Name can't be blank")));
    }

    @Test
    void updateRole() throws Exception {
        UUID amateurId=roleService.create(new RoleDTO("Amateur"));
        mockMvc.perform(put(String.format("/api/roles/%s", amateurId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"Amateur chef\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRoleSuccess() throws Exception {
        UUID homeChefId=roleService.create(new RoleDTO("Home chef"));
        mockMvc.perform(delete(String.format("/api/roles/%s", homeChefId)))
                .andExpect(status().isOk());
    }
}