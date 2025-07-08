package com.example.tasks.controller;

import com.example.tasks.dto.TaskDTO;
import com.example.tasks.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.tasks.model.Task;

import java.util.List;

@RestController //devuelve JSON
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskDTO taskDTO){
        Task savedTask = taskService.createTask(taskDTO);
        return ResponseEntity.ok(savedTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> taskList = taskService.getAllTasks();
        return ResponseEntity.ok(taskList);
    }
}
