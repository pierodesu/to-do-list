package dima.todolist.controller;

import dima.todolist.model.dto.TaskDto;
import dima.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping ("/task")
@Tag(name = "Контроллер по задачам", description = "Включает в себя CRUD по Task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping ("/getById/{id}")
    @ApiResponse(description = "Получение задач по ID")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity get(@PathVariable long id) {
        return taskService.get(id);
    }

    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponse(description = "Создание задачи")
    public ResponseEntity<String> create(@RequestBody TaskDto taskDto) {
        return taskService.create(taskDto);
    }

    @PutMapping("/updateStatus")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponse(description = "Обновление статуса задачи")
    public ResponseEntity<String> updateStatus(@RequestBody TaskDto taskDto) {
        return taskService.updateStatus(taskDto);
    }

    @PutMapping ("/update")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponse(description = "Обновление данных по задаче")
    public ResponseEntity<String> update(@RequestBody TaskDto taskDto) {
        return taskService.update(taskDto);
    }

    @DeleteMapping ("/deleteById/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponse(description = "Удаление задачи по ID")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return taskService.delete(id);
    }

    @GetMapping ("/getList")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponse(description = "Получение всех ваших задач")
    public ResponseEntity getList(@RequestParam int limit, @RequestParam int offset) {
        return taskService.getList(limit, offset);
    }

}
