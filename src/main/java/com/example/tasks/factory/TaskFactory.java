package com.example.tasks.factory;

import com.example.tasks.dto.TaskDTO;
import com.example.tasks.model.Task;

public class TaskFactory {

    public static Task toTask(TaskDTO dto){
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .responsible(dto.getResponsible())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .deadLine(dto.getDeadLine())
                .creationDate(dto.getCreationDate())
                .build();
    }

    public static TaskDTO toDto(Task task){
        return TaskDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .responsible(task.getResponsible())
                .status(task.getStatus())
                .priority(task.getPriority())
                .deadLine(task.getDeadLine())
                .creationDate(task.getCreationDate())
                .build();
    }
}
