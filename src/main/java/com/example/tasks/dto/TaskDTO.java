package com.example.tasks.dto;

import com.example.tasks.model.enums.TaskStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Responsible is required")
    private String responsible;

    private TaskStatus status;

    @NotNull(message = "Priority is required")
    @Min(value = 1, message = "Priority must be at least 1")
    @Max(value = 3, message = "Priority must be at most 3")
    private Integer priority;


    @Future(message = "Deadline must be in the future")
    private LocalDate deadLine;


    @PastOrPresent(message = "Creation date must be today or earlier")
    private LocalDate creationDate;
}
