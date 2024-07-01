package com.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
	List<Task> findByEmpployeeId(Long employeeId);
	List<Task>findByProjectId(Long projectId);
	List<Task> getTasksByEmployeeId(Long employeeId);
	Task findByTaskId(Long taskId);
}
