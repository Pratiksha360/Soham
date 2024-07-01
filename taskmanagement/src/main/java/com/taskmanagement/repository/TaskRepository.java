package com.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> getTasksByEmployee_Id(Long employeeId);

//	List<Task> findByProjectId(Long projectId);
	List<Task> findByProject_ProjectId(Long projectId);

	Task findByTaskId(Long taskId);
}
