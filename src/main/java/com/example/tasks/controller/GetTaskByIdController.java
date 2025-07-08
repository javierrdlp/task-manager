package com.example.tasks.controller;

import com.example.tasks.model.Task;
import com.example.tasks.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/tasks")
public class GetTaskByIdController {

    private final TaskService taskService;

    public GetTaskByIdController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id){
        Task task = taskService.getTask(id);
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
