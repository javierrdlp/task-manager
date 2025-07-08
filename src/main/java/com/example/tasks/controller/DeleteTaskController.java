package com.example.tasks.controller;

import com.example.tasks.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/tasks")
public class DeleteTaskController {

    private final TaskService taskService;

    public DeleteTaskController(TaskService taskService) { this.taskService = taskService;  }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Task with id " + id + " was succesfully deleted");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
