package com.example.tasks.model;

import com.example.tasks.dto.TaskDTO;
import com.example.tasks.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String responsible;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(nullable = false)
    private Integer priority;

    private LocalDate deadLine;

    private LocalDate creationDate;

    public void updateFromDTO(TaskDTO dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.responsible = dto.getResponsible();
        this.status = dto.getStatus();
        this.priority = dto.getPriority();
        this.deadLine = dto.getDeadLine();
    }

}
