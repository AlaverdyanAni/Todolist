package pro.sky.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.todolist.dto.UserDto;
import pro.sky.todolist.service.UserService;
@RestController
@RequestMapping("/user")
@Tag(name = "Пользователь", description = "CRUD методы для работы с пользователями")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "Добавление пользователя")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Пользователь успешно добавлен"),
                    @ApiResponse(responseCode = "400", description = "Невалидное поле!"),
                    @ApiResponse(responseCode = "409", description = "Пользователь с таким названием уже есть в БД"),
            }
    )
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.create(userDto));
    }

    @Operation(summary = "Получение пользователя по id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Пользователь успешно найден"),
                    @ApiResponse(responseCode = "404", description = "Пользователь с таким id не найден"),
            }
    )
    @GetMapping("/{id}")
    public UserDto get(@PathVariable long id) {
        return userService.read(id);
    }

    @Operation(summary = "Обновление пользователя по id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлён"),
                    @ApiResponse(responseCode = "400", description = "Невалидное поле!"),
                    @ApiResponse(responseCode = "404", description = "Пользователь с таким id не найден"),
            }
    )
    @PutMapping("/{id}")
    public UserDto update(@PathVariable long id, @RequestBody @Valid UserDto userDto) {
        return userService.update(id, userDto);
    }

    @Operation(summary = "Удаление пользователя по id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Пользователь успешно удалён"),
                    @ApiResponse(responseCode = "404", description = "Пользователь с таким id не найден"),
            }
    )
    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable long id) {
        return userService.delete(id);
    }

}
