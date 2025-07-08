package com.example.tasks.controller;

import com.example.tasks.model.Task;
import com.example.tasks.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class GetTasksByPriorityController {

    private final TaskService taskService;

    public GetTasksByPriorityController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/priority")
    public ResponseEntity<List<Task>> getTasksByPriority(){
        List<Task> taskList = taskService.getTasksByPriority();

        return ResponseEntity.ok(taskList);
    }
}
