package pro.sky.todolist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import pro.sky.todolist.annotation.ValueOfEnum;
import pro.sky.todolist.model.Status;

@Getter
@Setter
public class TaskDtoIn {

    @Schema(name = "id", description = "id задачи")
    private Long id;

    @Schema(name = "name", description = "Название", maxLength = 50, example = "Писать статью")
    @NotBlank
    @Size(max = 50)
    private String name;

    @Schema(name = "description", description = "Описание", maxLength = 120, example = "Писать статью")
    @NotBlank
    @Size(max = 120)
    private String description;

    @Schema(name = "status", description = "Статус", example = "NEW, IN_PROGRESS, COMPLETED")
    @NotNull
    @ValueOfEnum(enumClass = Status.class, message = "Invalid Status")
    private Status status;

    @Schema(name = "userId", description = "Имя пользователя",minimum = "1", example = "1")
    @NotBlank
    @Positive
    private long userId;

    @Schema(name = "labelId", description = "Название категории", minimum = "1", example = "1")
    @NotBlank
    @Positive
    private long labelId;
}

