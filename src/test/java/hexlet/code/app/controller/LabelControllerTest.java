package hexlet.code.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import hexlet.code.app.config.SpringConfigs;
import hexlet.code.app.utils.TestUtils;
import hexlet.code.dto.LabelDtoRq;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskRepository;
import hexlet.code.repository.model.Label;
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
import static hexlet.code.app.utils.TestUtils.asJson;
import static hexlet.code.app.utils.TestUtils.fromJson;
import static hexlet.code.controller.LabelController.LABEL_PATH;
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
public class LabelControllerTest {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private TaskRepository taskRepository;

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
    public void getLabelByIdTest() throws Exception {
        utils.regDefaultLabel();
        final Label expectedLabel = labelRepository.findAll().get(0);
        final var response = mockMvc.perform(
                        get(baseUrl + LABEL_PATH + "/{id}", expectedLabel.getId())
                                .header(AUTHORIZATION, utils.generateToken())
                ).andExpect(status().isOk())
                .andReturn()
                .getResponse();

        final Label label = fromJson(response.getContentAsString(), new TypeReference<>() {
        });

        assertEquals(expectedLabel.getId(), label.getId());
        assertEquals(expectedLabel.getName(), label.getName());
//        assertEquals(taskRepository.findAll().get(0).getLabel().get(0).getName(), label.getName());
    }

    @Test
    public void getLabelsTest() throws Exception {
        utils.regDefaultLabel();
        final List<Label> expectedLabels = labelRepository.findAll();
        final var response = mockMvc.perform(
                        get(baseUrl + LABEL_PATH)
                                .header(AUTHORIZATION, utils.generateToken()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        final List<Label> labels = fromJson(response.getContentAsString(), new TypeReference<>() {
        });

        assertEquals(expectedLabels.size(), labels.size());
        assertEquals(expectedLabels.get(0).getId(), labels.get(0).getId());
        assertEquals(expectedLabels.get(1).getName(), labels.get(1).getName());
    }

    @Test
    public void createLabelTest() throws Exception {
        final var label = new LabelDtoRq("Label 1");

        mockMvc.perform(post(baseUrl + LABEL_PATH)
                        .content(asJson(label))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, utils.generateToken())
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertEquals(labelRepository.findAll().size(), 1);
        assertEquals(labelRepository.findAll().get(0).getName(), "Label 1");
    }

    @Test
    public void updateUserTest() throws Exception {
        utils.regDefaultLabel();


        final var expectedLabel = new LabelDtoRq("Label 1");

        final var response = mockMvc.perform(
                        put(baseUrl + LABEL_PATH + "/{id}", labelRepository.findAll().get(0).getId())
                                .content(asJson(expectedLabel))
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, utils.generateToken()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertEquals(expectedLabel.getName(), labelRepository.findAll().get(0).getName());
    }

    @Test
    public void deleteLabel() throws Exception {
        utils.regDefaultLabel();

        mockMvc.perform(delete(baseUrl + LABEL_PATH + "/{id}", labelRepository.findAll().get(0).getId())
                        .header(AUTHORIZATION, utils.generateToken()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertEquals(labelRepository.findAll().size(), 1);
    }
}
