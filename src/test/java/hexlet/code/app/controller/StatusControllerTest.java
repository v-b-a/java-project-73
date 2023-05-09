package hexlet.code.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import hexlet.code.app.config.SpringConfigs;
import hexlet.code.app.utils.TestUtils;
import hexlet.code.repository.StatusRepository;
import hexlet.code.repository.model.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static hexlet.code.app.config.SpringConfigs.TEST_PROFILE;
import static hexlet.code.app.utils.TestUtils.fromJson;
import static hexlet.code.controller.StatusController.STATUS_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles(TEST_PROFILE)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringConfigs.class)
public class StatusControllerTest {

    @Autowired
    private StatusRepository statusRepository;


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
    public void getStatusByIdTest() throws Exception {
        utils.regDefaultStatus();
        Status expectedStatus = statusRepository.findAll().get(0);

        final var response = mockMvc.perform(
                        get(baseUrl + STATUS_PATH + "/{id}", expectedStatus.getId())
                                .header(AUTHORIZATION, utils.generateToken())
                ).andExpect(status().isOk())
                .andReturn()
                .getResponse();

        final Status actual = fromJson(response.getContentAsString(), new TypeReference<>() {
        });

        assertEquals(expectedStatus.getId(), actual.getId());
        assertEquals(expectedStatus.getName(), actual.getName());
    }

    @Test
    public void getStatuses() throws Exception {
        utils.regDefaultStatus();
        final List<Status> expected = statusRepository.findAll();
        final var response = mockMvc.perform(
                        get(baseUrl + STATUS_PATH)
                                .header(AUTHORIZATION, utils.generateToken())
                ).andExpect(status().isOk())
                .andReturn()
                .getResponse();

        final List<Status> actual = fromJson(response.getContentAsString(), new TypeReference<>() {
        });
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(1).getName(), actual.get(1).getName());
    }

//    @Test
//    public void createStatusTest() throws Exception {
//        utils.regDefaultUsers();
//        StatusDtoRq status = new StatusDtoRq("ToDo");
//
//        mockMvc.perform(
//                        post(baseUrl + STATUS_PATH)
//                                .content(asJson(status))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .header(AUTHORIZATION, utils.generateToken()))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse();
//
//        assertEquals(statusRepository.findAll().size(), 1);
//        assertEquals(statusRepository.findAll().get(0).getName(), "ToDo");
//    }
//
//    @Test
//    public void updateStatusTest() throws Exception {
//        utils.regDefaultStatus();
//
//
//        StatusDtoRq status = new StatusDtoRq("ToDo2");
//
//        final var response = mockMvc.perform(
//                        put(baseUrl + STATUS_PATH + "/{id}", statusRepository.findAll().get(0).getId())
//                                .content(asJson(status))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .header(AUTHORIZATION, utils.generateToken()))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse();
//
//        assertEquals(status.getName(), statusRepository.findAll().get(0).getName());
//    }

    @Test
    public void deleteStatusTest() throws Exception {
        utils.regDefaultStatus();

        final var response = mockMvc.perform(
                        delete(baseUrl + STATUS_PATH + "/{id}", statusRepository.findAll().get(0).getId())
                                .header(AUTHORIZATION, utils.generateToken()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertEquals(statusRepository.findAll().size(), 1);
    }

}
