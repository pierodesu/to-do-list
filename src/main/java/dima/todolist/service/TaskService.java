package dima.todolist.service;

import dima.todolist.configuration.JwtTokenUtils;
import dima.todolist.model.Task;
import dima.todolist.model.User;
import dima.todolist.model.dto.TaskDto;
import dima.todolist.repository.TaskRepository;
import dima.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserRepository userRepository;

    public ResponseEntity get(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        User user = userRepository.findByLogin(jwtTokenUtils.getCurrentLogin());

        if (task.isPresent() && task.get().getUser() == user) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.badRequest().body("Задача не найдена");
        }
    }

    public ResponseEntity<String> create(TaskDto taskDto) {
        Task task = new Task();

        String currentLogin = jwtTokenUtils.getCurrentLogin();
        User user = userRepository.findByLogin(currentLogin);

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setUser(user);
        task.setStartDate(taskDto.getStartDate());
        task.setDueDate(taskDto.getDueDate());
        taskRepository.save(task);

        return ResponseEntity.ok("Успешное создан");
    }

    public ResponseEntity getList(int limit, int offset) {
        Pageable pageable = PageRequest.of(limit, offset);
        User user = userRepository.findByLogin(jwtTokenUtils.getCurrentLogin());

        if (user != null) {
            return ResponseEntity.ok(taskRepository.findAll(user.getId(), pageable));
        } else {
            return ResponseEntity.badRequest().body("Задачи не найдены");
        }
    }

    public ResponseEntity<String> update(TaskDto taskDto) {
        Optional<Task> task = taskRepository.findById(taskDto.getId());
        User user = userRepository.findByLogin(jwtTokenUtils.getCurrentLogin());

        if (task.isPresent() && task.get().getUser() == user) {
            Task updateTask = task.get();
            updateTask.setTitle(taskDto.getTitle());
            updateTask.setDescription(taskDto.getDescription());
            updateTask.setStartDate(taskDto.getStartDate());
            updateTask.setDueDate(taskDto.getDueDate());
            taskRepository.save(updateTask);
            return ResponseEntity.ok("Успешное обновление");
        } else {
            return ResponseEntity.badRequest().body("Задача не найдена");
        }
    }

    public ResponseEntity<String> updateStatus(TaskDto taskDto) {
        Optional<Task> task = taskRepository.findById(taskDto.getId());
        User user = userRepository.findByLogin(jwtTokenUtils.getCurrentLogin());

        if (task.isPresent() && task.get().getUser() == user) {
            Task updateTask = task.get();
            updateTask.setCompleted(taskDto.getCompleted());
            taskRepository.save(updateTask);
            return ResponseEntity.ok("Статус обновлен");
        } else {
            return ResponseEntity.badRequest().body("Задача не найдена");
        }
    }

    public ResponseEntity<String> delete(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        User user = userRepository.findByLogin(jwtTokenUtils.getCurrentLogin());

        if (task.isPresent() && task.get().getUser() == user) {
            taskRepository.delete(task.get());
            return ResponseEntity.ok("Успешно удалено");
        } else {
            return ResponseEntity.badRequest().body("Задача не найдена");
        }


    }

}
