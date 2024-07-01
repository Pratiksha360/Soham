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
    	List<Task> task = taskRepository.findByEmpployeeId(employeeId);
		ResponseStructure<List<Task>> structure = new ResponseStructure<List<Task>>();
		if (task != null) {
			structure.setMessage("Tasks Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(taskRepository.getTasksByEmployeeId(employeeId));

			return new ResponseEntity<ResponseStructure<List<Task>>>(structure, HttpStatus.FOUND);
		} else {

			structure.setMessage("Task Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<List<Task>>>(structure, HttpStatus.NOT_FOUND);
		}
        
    }

    public ResponseEntity<ResponseStructure<List<Task>>> getTasksByProjectId(Long projectId) {
      	List<Task> task = taskRepository.findByProjectId(projectId);
    		ResponseStructure<List<Task>> structure = new ResponseStructure<List<Task>>();
    		if (task != null) {
    			structure.setMessage("Tasks Found successfully");
    			structure.setStatus(HttpStatus.FOUND.value());
    			structure.setData(taskRepository.findByProjectId(projectId));

    			return new ResponseEntity<ResponseStructure<List<Task>>>(structure, HttpStatus.FOUND);
    		} else {

    			structure.setMessage("Task Not Found");
    			structure.setStatus(HttpStatus.NOT_FOUND.value());
    			structure.setData(null);
    			return new ResponseEntity<ResponseStructure<List<Task>>>(structure, HttpStatus.NOT_FOUND);
    		}
    }

    public ResponseEntity<ResponseStructure<Task>>  saveTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        ResponseStructure<Task> structure = new ResponseStructure<Task>();
		structure.setMessage("Task saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(taskRepository.save(task));
		return new ResponseEntity<ResponseStructure<Task>>(structure, HttpStatus.CREATED);
        
    }

    public ResponseEntity<ResponseStructure<Task>> updateTask(long tsakId,Task task) {
    	
 		ResponseStructure<Task> structure = new ResponseStructure<Task>();
 		Task task2 = taskRepository.findByTaskId(tsakId);
 		if (task2 != null) {
 			 task.setUpdatedAt(LocalDateTime.now());
 			 taskRepository.save(task);
 			structure.setMessage("Project  Updated successfully");
 			structure.setStatus(HttpStatus.OK.value());
 			structure.setData(task);
 			return new ResponseEntity<ResponseStructure<Task>>(structure, HttpStatus.OK);
 		} else {
 			structure.setMessage("Project  Not Found");
 			structure.setStatus(HttpStatus.NOT_FOUND.value());
 			structure.setData(task);
 			return new ResponseEntity<ResponseStructure<Task>>(structure, HttpStatus.NOT_FOUND);
 		}
       
    }
}
