package com.taskmanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taskmanagement.entity.Project;
import com.taskmanagement.entity.Task;
import com.taskmanagement.repository.TaskRepository;
import com.taskmanagement.util.ResponseStructure;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity<ResponseStructure<List<Task>>> getTasksByEmployeeId(Long employeeId) {
        List<Task> tasks = taskRepository.getTasksByEmployee_Id(employeeId);
        ResponseStructure<List<Task>> structure = new ResponseStructure<>();
        if (!tasks.isEmpty()) {
            structure.setMessage("Tasks Found successfully");
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setData(tasks);
            return new ResponseEntity<>(structure, HttpStatus.FOUND);
        } else {
            structure.setMessage("Task Not Found");
            structure.setStatus(HttpStatus.NOT_FOUND.value());
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<List<Task>>> getTasksByProjectId(Long projectId) {
        List<Task> tasks = taskRepository.findByProject_ProjectId(projectId);
        ResponseStructure<List<Task>> structure = new ResponseStructure<>();
        if (!tasks.isEmpty()) {
            structure.setMessage("Tasks Found successfully");
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setData(tasks);
            return new ResponseEntity<>(structure, HttpStatus.FOUND);
        } else {
            structure.setMessage("Task Not Found");
            structure.setStatus(HttpStatus.NOT_FOUND.value());
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<Task>> saveTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        ResponseStructure<Task> structure = new ResponseStructure<>();
        structure.setMessage("Task saved successfully");
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setData(taskRepository.save(task));
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<Task>> updateTask(long taskId, Task task) {
        ResponseStructure<Task> structure = new ResponseStructure<>();
        Task existingTask = taskRepository.findByTaskId(taskId);
        if (existingTask != null) {
            task.setUpdatedAt(LocalDateTime.now());
            taskRepository.save(task);
            structure.setMessage("Task updated successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(task);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            structure.setMessage("Task not found");
            structure.setStatus(HttpStatus.NOT_FOUND.value());
            structure.setData(task);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }
}
