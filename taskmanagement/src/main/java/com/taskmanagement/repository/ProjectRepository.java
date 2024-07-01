package com.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.taskmanagement.entity.Project;
import com.taskmanagement.entity.Task;
import com.taskmanagement.util.ResponseStructure;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	Project findByProjectId(Long projectId);

	Project findByEmployeeId(Long employeeId);

	List<Project> getProjectsByEmployeeId(Long employeeId);

	List<Project> findAllProjects();

}
