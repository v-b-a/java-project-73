package hexlet.code.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusDtoRq {
    @NotBlank(message = "Name is required")
    @Size(min = 1, message = "Min length name is 1 char")
    private String name;
}
