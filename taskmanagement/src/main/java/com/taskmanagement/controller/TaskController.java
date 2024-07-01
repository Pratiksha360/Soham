package com.taskmanagement.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.taskmanagement.entity.Task;
import com.taskmanagement.service.TaskService;
import com.taskmanagement.util.ResponseStructure;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ResponseStructure<List<Task>>>  getTasksByEmployee(@PathVariable Long employeeId) {
        return taskService.getTasksByEmployeeId(employeeId);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ResponseStructure<List<Task>>> getTasksByProject(@PathVariable Long projectId) {
        return taskService.getTasksByProjectId(projectId);
    }

    @PostMapping("/saveTask")
    public ResponseEntity<ResponseStructure<Task>> createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @PutMapping("/updateTask")
    public ResponseEntity<ResponseStructure<Task>> updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setTaskID(id);
        return taskService.updateTask(id, task);
    }
}
