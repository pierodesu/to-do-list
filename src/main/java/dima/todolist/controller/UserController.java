package dima.todolist.controller;

import dima.todolist.model.dto.UserDto;
import dima.todolist.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "Контроллер по пользователям", description = "Включает в себя CRUD по User")
public class UserController {

    private final UserService userService;

    @GetMapping ("/getUser")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponse(description = "Получение информации по пользователю")
    public ResponseEntity get() {
        return userService.get();
    }

    @PutMapping ("/update")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponse(description = "Обновление информации по пользователю")
    public ResponseEntity<String> update(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    @DeleteMapping ("/deleteUser")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponse(description = "Удаление пользователя")
    public ResponseEntity<String> delete() {
        return userService.delete();
    }

}
