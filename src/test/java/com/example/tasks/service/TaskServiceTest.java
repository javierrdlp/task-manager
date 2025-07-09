package com.example.tasks.service;

import com.example.tasks.dto.TaskDTO;
import com.example.tasks.factory.TaskFactory;
import com.example.tasks.model.Task;
import com.example.tasks.model.enums.TaskStatus;
import com.example.tasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private TaskRepository taskRepository;
    private  TaskService taskService;

    @BeforeEach
    void setup(){
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }
    @Test
    void testCreateTask_withValidData_returnsSavedTask() {
        TaskDTO dto = TaskDTO.builder()
                .title("Test")
                .responsible("Javier")
                .status(TaskStatus.PENDING)
                .priority(1)
                .deadLine(LocalDate.now().plusDays(2))
                .creationDate(LocalDate.now())
                .build();

        Task task = TaskFactory.toTask(dto);

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.createTask(dto);

        assertEquals(dto.getTitle(), result.getTitle());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testGetAllTasks_returnsTaskList(){
        List<Task> tasks = List.of(
                Task.builder().title("Task1").responsible("Responsible1").priority(1).build(),
                Task.builder().title("Task2").responsible("Responsible2").priority(2).build(),
                Task.builder().title("Task3").responsible("Responsible3").priority(3).build()
        );

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> results = taskService.getAllTasks();

        assertEquals(3, results.size());
        assertEquals("Task1", results.get(0).getTitle());
        assertEquals("Responsible2", results.get(1).getResponsible());
        assertEquals(3, results.get(2).getPriority());

        verify(taskRepository, times(1)).findAll();

    }
    @Test
    void testGetTask_whenNotFound_throwsException() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> taskService.getTask(1L));

        assertEquals("Task not found with id: 1", exception.getMessage());
    }
}
