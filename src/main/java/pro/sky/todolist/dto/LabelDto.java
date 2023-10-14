package pro.sky.todolist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelDto {

    @Schema(name = "id", description = "id категории")
    private Long id;

    @Schema(name = "name", description = "Название категории", maxLength = 20, example = "Писать статью")
    @NotBlank
    @Size(max = 20)
    private String name;

}