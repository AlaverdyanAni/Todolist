package pro.sky.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.todolist.dto.LabelDto;
import pro.sky.todolist.service.LabelService;

@RestController
@RequestMapping("/label")
@Tag(name = "Категория", description = "CRUD методы для работы с категориями")
public class LabelController {
    private final LabelService labelService;
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @Operation(summary = "Добавление категории")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Категория успешно добавлен"),
                    @ApiResponse(responseCode = "400", description = "Название категории пустое"),
                    @ApiResponse(responseCode = "409", description = "Категория с таким названием уже есть в БД"),
            }
    )
    @PostMapping
    public ResponseEntity<LabelDto> create(@RequestBody @Valid LabelDto labelDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(labelService.create(labelDto));
    }


    @Operation(summary = "Получение категории по id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Категория успешно найден"),
                    @ApiResponse(responseCode = "404", description = "Категория с таким id не найден"),
            }
    )
    @GetMapping("/{id}")
    public LabelDto get(@PathVariable long id) {
        return labelService.read(id);
    }

    @Operation(summary = "Обновление категории по id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Категория успешно обновлён"),
                    @ApiResponse(responseCode = "400", description = "Название категории пустое"),
                    @ApiResponse(responseCode = "404", description = "Категория с таким id не найден"),
            }
    )
    @PutMapping("/{id}")
    public LabelDto update(@PathVariable long id, @RequestBody @Valid LabelDto labelDto) {
        return labelService.update(id, labelDto);
    }

    @Operation(summary = "Удаление категории по id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Категория успешно удалён"),
                    @ApiResponse(responseCode = "404", description = "Категория с таким id не найден"),
            }
    )
    @DeleteMapping("/{id}")
    public LabelDto delete(@PathVariable long id) {
        return labelService.delete(id);
    }
}
