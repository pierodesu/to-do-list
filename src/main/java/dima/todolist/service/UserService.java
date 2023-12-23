package dima.todolist.service;

import dima.todolist.configuration.JwtTokenUtils;
import dima.todolist.model.User;
import dima.todolist.model.dto.UserDto;
import dima.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;

    public ResponseEntity get() {
        User user = userRepository.findByLogin(jwtTokenUtils.getCurrentLogin());

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }
    }

    public ResponseEntity<String> update(UserDto userDto) {
        User user = userRepository.findByLogin(jwtTokenUtils.getCurrentLogin());

        if (user != null) {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            userRepository.save(user);
            return ResponseEntity.ok("Успешное обновление");
        } else {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }
    }

    public ResponseEntity<String> delete() {
        User user = userRepository.findByLogin(jwtTokenUtils.getCurrentLogin());

        if (user != null) {
            userRepository.delete(user);
            return ResponseEntity.ok("Успешно удалено");
        } else {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }
    }

}
