package pro.sky.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.todolist.dto.TaskDtoIn;
import pro.sky.todolist.dto.TaskDtoOut;
import pro.sky.todolist.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
@Tag(name = "Задачи", description = "CRUD методы для работы с задачами")
public class TaskController {
        private final TaskService taskService;
        public TaskController(TaskService taskService) {
            this.taskService = taskService;
        }

        @Operation(summary = "Добавление задачи")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "201", description = "Задача успешно добавлена"),
                        @ApiResponse(responseCode = "400", description = "Невалидное поле в DTO"),
                        @ApiResponse(responseCode = "404", description = "Задача не найдена по id"),
                }
        )
        @PostMapping
        public ResponseEntity<TaskDtoOut> create(@RequestBody @Valid TaskDtoIn taskDtoIn) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(taskService.create(taskDtoIn));
        }

        @Operation(summary = "Обновление задачи по id")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Задача успешно обновлена"),
                        @ApiResponse(responseCode = "400", description = "Невалидное поле в DTO"),
                        @ApiResponse(responseCode = "404", description = "Задача не найдена по id"),
                }
        )
        @PutMapping("/{id}")
        public TaskDtoOut update(@PathVariable long id,
                                      @RequestBody @Valid TaskDtoIn taskDtoIn) {
            return taskService.update(id, taskDtoIn);
        }

        @Operation(summary = "Получение задачи по id")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Задача успешно найдена"),
                        @ApiResponse(responseCode = "404", description = "Задача с таким id не найдена"),
                }
        )
        @GetMapping("/{id}")
        public TaskDtoOut get(@PathVariable long id) {
            return taskService.read(id);
        }
        @Operation(summary = "Удаление задачи по id")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Задача успешно удалена"),
                        @ApiResponse(responseCode = "404", description = "Задача с таким id не найдена"),
                }
        )
        @DeleteMapping("/{id}")
        public TaskDtoOut delete(@PathVariable long id) {
            return taskService.delete(id);
        }

        @Operation(summary = "Получение списка задач по параметрам")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Задача успешно найдены"),
                        @ApiResponse(responseCode = "400", description = "Некорректные параметры")
                }
        )
        @GetMapping
        public List<TaskDtoOut> getUserAllTasks(@RequestParam long userId) {
                return taskService.userAllTasks(userId);
        }


    }

