package hexlet.code.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusDtoRq {
    @NotBlank(message = "Name is required")
    @Size(min = 1, message = "Min length name is 1 char")
    private String name;
}
