package com.example.tasks.controller;

import com.example.tasks.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
