package com.example.tasks.service;

import com.example.tasks.dto.TaskDTO;
import com.example.tasks.factory.TaskFactory;
import com.example.tasks.model.Task;
import com.example.tasks.model.enums.TaskStatus;
import com.example.tasks.repository.TaskRepository;
import org.springframework.data.domain.Sort;
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

        //Check date compatibility with priority
        if(task.getPriority() == 1){
            long daysBetween = task.getDeadLine().toEpochDay() - task.getCreationDate().toEpochDay();
            if(daysBetween > 3){
                throw new IllegalArgumentException(
                        "For high priority tasks, deadline must be within 3 days after creation date"
                );
            }
        }

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public List<Task> getTasksByPriority() {
        return taskRepository.findAll(Sort.by("deadLine").ascending());
    }

    public Task getTask(Long id){
        return taskRepository.findById(id)
                             .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

    }

    public void deleteTask(Long id){
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task with id " + id + " does not exist");
        }
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, TaskDTO dto){
        Task existingTask = taskRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        if(existingTask.getStatus() == TaskStatus.COMPLETED){
            throw new IllegalArgumentException("Task with COMPLETED status cannot be update");
        }
        existingTask.updateFromDTO(dto);
        return taskRepository.save(existingTask);
    }
}
