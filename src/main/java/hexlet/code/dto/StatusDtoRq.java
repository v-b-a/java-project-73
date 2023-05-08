package hexlet.code.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class StatusDtoRq {
    @NotBlank(message = "Name is required")
    @Size(min = 1, message = "Min length name is 1 char")
    private String name;
}
