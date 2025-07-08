package com.example.tasks.service;

import com.example.tasks.dto.TaskDTO;
import com.example.tasks.factory.TaskFactory;
import com.example.tasks.model.Task;
import com.example.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task createTask(TaskDTO dto){
        Task task = TaskFactory.toTask(dto);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTask(Long id){
        return taskRepository.findById(id)
                             .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

    }
}
