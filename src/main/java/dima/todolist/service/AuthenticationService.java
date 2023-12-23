package dima.todolist.service;

import dima.todolist.configuration.JwtTokenUtils;
import dima.todolist.model.User;
import dima.todolist.model.dto.LoginDto;
import dima.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;

    public ResponseEntity<String> register(LoginDto loginDto) {

        if (userRepository.findByLogin(loginDto.getLogin()) != null) {
            return ResponseEntity.badRequest().body("Пользователь уже зарегестрирован");
        }

        String password = passwordEncoder.encode(loginDto.getPassword());

        User user = new User();
        user.setFirstName(loginDto.getFirstName());
        user.setLastName(loginDto.getLastName());
        user.setLogin(loginDto.getLogin());
        user.setPassword(password);

        userRepository.save(user);

        return ResponseEntity.ok("Успешная регистрация");

    }

    public ResponseEntity<String> auhtenticate(LoginDto loginDto) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getPassword()));

        User user = userRepository.findByLogin(loginDto.getLogin());

        String jwtToken = jwtTokenUtils.generateToken(new HashMap<>(), user);

        return ResponseEntity.ok(jwtToken);

    }

}
