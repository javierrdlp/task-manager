package com.example.tasks.model;

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

    private String title;

    private String description;

    private String responsible;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Integer priority;

    private LocalDate deadLine;

    private LocalDate creationDate;

}
