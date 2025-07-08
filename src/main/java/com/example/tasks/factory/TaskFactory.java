package com.example.tasks.factory;

import com.example.tasks.dto.TaskDTO;
import com.example.tasks.model.Task;

public class TaskFactory {

    public static Task toTask(TaskDTO dto){
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    public static TaskDTO toDto(Task task){
        return TaskDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .build();
    }
}
