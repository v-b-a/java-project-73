package hexlet.code.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.filter.JWTHelper;
import hexlet.code.repository.StatusRepository;
import hexlet.code.repository.model.Status;
import hexlet.code.repository.model.User;
import hexlet.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;


@Component
public class TestUtils {

    public static final String TEST_EMAIL = "email@email.com";
    public static final String TEST_EMAIL_2 = "email2@email.com";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private JWTHelper jwtHelper;

    public String generateToken() {
        return jwtHelper.expiring(Map.of("username", "email@email.com"));
    }

    public void tearDown() {
        userRepository.deleteAll();
        statusRepository.deleteAll();
    }

//    public User getUserByEmail(final String email) {
//        return userRepository.findByEmail(email).get();
//    }

    public void regDefaultUsers() {
        userRepository.save(User.builder()
                .email(TEST_EMAIL)
                .firstName("fname")
                .lastName("lname")
                .password("pwd123")
                .build());

        userRepository.save(User.builder()
                .email(TEST_EMAIL_2)
                .firstName("fname2")
                .lastName("lname2")
                .password("pwd1234")
                .build());
    }

    public void regDefaultStatus() {
        statusRepository.save(Status.builder()
                .name("To Do")
                .build());
        statusRepository.save(Status.builder()
                .name("Ready")
                .build());
    }


    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();

    public static String asJson(final Object object) throws JsonProcessingException {
        return MAPPER.writeValueAsString(object);
    }

    public static <T> T fromJson(final String json, final TypeReference<T> to) throws JsonProcessingException {
        return MAPPER.readValue(json, to);
    }
}
