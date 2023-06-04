package hexlet.code.service.mapper;

import hexlet.code.dto.UserDtoRq;
import hexlet.code.dto.UserDtoRs;
import hexlet.code.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserDtoRs toUserDto(User user) {
        if (user == null) return null;
        return UserDtoRs.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createAt(user.getCreatedAt())
                .build();
    }

    public User toUser(UserDtoRq user) {
        return User.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
    }
}
