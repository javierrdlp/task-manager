package com.example.tasks.repository;

import com.example.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
/*
Al extender de JpaRepository permite usar las funciones CRUD:
taskRepository.save(task);         // crea o actualiza una tarea
taskRepository.findById(1L);       // busca una tarea por ID
taskRepository.findAll();          // devuelve todas las tareas
taskRepository.deleteById(1L);     // borra una tarea por ID
 */