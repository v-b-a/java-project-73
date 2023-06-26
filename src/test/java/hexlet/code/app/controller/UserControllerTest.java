package hexlet.code.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import hexlet.code.app.config.SpringConfigs;
import hexlet.code.app.utils.TestUtils;
import hexlet.code.repository.model.User;
import hexlet.code.repository.UserRepository;
import hexlet.code.dto.UserDtoRq;
import hexlet.code.dto.UserDtoRs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static hexlet.code.app.config.SpringConfigs.TEST_PROFILE;
import static hexlet.code.app.utils.TestUtils.TEST_EMAIL;
import static hexlet.code.app.utils.TestUtils.asJson;
import static hexlet.code.app.utils.TestUtils.fromJson;
import static hexlet.code.controller.UserController.USERS_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles(TEST_PROFILE)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringConfigs.class)
public class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Value("${base-url}")
    @Autowired
    private String baseUrl;

    @Autowired
    private TestUtils utils;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void clear() {
        utils.tearDown();
    }

    @Test
    public void getUserByIdTest() throws Exception {
        utils.regDefaultUsers();
        final User expectedUser = userRepository.findAll().get(0);
        final var response = mockMvc.perform(
                        get(baseUrl + USERS_PATH + "/{id}", expectedUser.getId())
                                .header(AUTHORIZATION, utils.generateToken())
                ).andExpect(status().isOk())
                .andReturn()
                .getResponse();

        final UserDtoRs user = fromJson(response.getContentAsString(), new TypeReference<>() {
        });

        assertEquals(expectedUser.getId(), user.getId());
        assertEquals(expectedUser.getEmail(), user.getEmail());
        assertEquals(expectedUser.getFirstName(), user.getFirstName());
        assertEquals(expectedUser.getLastName(), user.getLastName());
    }

    @Test
    public void getUsersTest() throws Exception {
        utils.regDefaultUsers();
        final List<User> expectedUsers = userRepository.findAll();
        final var response = mockMvc.perform(
                        get(baseUrl + USERS_PATH)
                                .header(AUTHORIZATION, utils.generateToken()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        final List<UserDtoRs> userDtoRs = fromJson(response.getContentAsString(), new TypeReference<List<UserDtoRs>>() {
        });

        assertEquals(expectedUsers.size(), userDtoRs.size());
        assertEquals(expectedUsers.get(0).getId(), userDtoRs.get(0).getId());
        assertEquals(expectedUsers.get(1).getLastName(), userDtoRs.get(1).getLastName());
        assertEquals(expectedUsers.get(0).getEmail(), userDtoRs.get(0).getEmail());
    }

    @Test
    public void createUserTest() throws Exception {
        final var user = UserDtoRq.builder()
                .email(TEST_EMAIL)
                .firstName("fname")
                .lastName("lname")
                .password("pwd123")
                .build();

        final var response = mockMvc.perform(
                        post(baseUrl + USERS_PATH)
                                .content(asJson(user))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertEquals(userRepository.findAll().size(), 1);
        assertEquals(userRepository.findAll().get(0).getEmail(), TEST_EMAIL);
    }

    @Test
    public void updateUserTest() throws Exception {
        utils.regDefaultUsers();


        final var expectedUser = UserDtoRq.builder()
                .email(TEST_EMAIL)
                .firstName("fnameNew")
                .lastName("lnameNew")
                .password("pwd123New")
                .build();

        final var response = mockMvc.perform(
                        put(baseUrl + USERS_PATH + "/{id}", userRepository.findAll().get(0).getId())
                                .content(asJson(expectedUser))
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, utils.generateToken()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertEquals(expectedUser.getFirstName(), userRepository.findAll().get(0).getFirstName());
        assertEquals(expectedUser.getLastName(), userRepository.findAll().get(0).getLastName());
    }

    @Test
    public void deleteUser() throws Exception {
        utils.regDefaultUsers();

        final var response = mockMvc.perform(
                        delete(baseUrl + USERS_PATH + "/{id}", userRepository.findAll().get(0).getId())
                                .header(AUTHORIZATION, utils.generateToken()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertEquals(userRepository.findAll().size(), 1);
    }
}
