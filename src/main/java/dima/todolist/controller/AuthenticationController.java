package dima.todolist.controller;

import dima.todolist.model.dto.LoginDto;
import dima.todolist.service.AuthenticationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "Контроллер по Авторизации", description = "Включает в себя Регистрацию и Авторизацию")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping ("/register")
    @ApiResponse(description = "Регистрация нового пользователя")
    public ResponseEntity<String> register(@RequestBody LoginDto loginDto) {
        return authenticationService.register(loginDto);
    }

    @PostMapping ("/authenticate")
    @ApiResponse(description = "Авторизация по существующему аккаунту")
    public ResponseEntity<String> auhtenticate(@RequestBody LoginDto loginDto) {
        return authenticationService.auhtenticate(loginDto);
    }

}
