package pro.sky.todolist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @Schema(name = "id", description = "id пользователя")
    private Long id;

    @Schema(name = "name", description = "Имя пользователя", maxLength = 10, example = "Ани")
    @NotBlank
    @Size(max = 10)
    private String name;

    @Schema(name = "email", description = "Email пользователя", maxLength = 30,example = "ani-alaverdyan@mail.ru")
    @NotBlank
    @Size(max = 30)
    private String email;

}
