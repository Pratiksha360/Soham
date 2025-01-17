package com.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.taskmanagement.entity.Project;
import com.taskmanagement.entity.Task;
import com.taskmanagement.util.ResponseStructure;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	Project findByProjectId(Long projectId);

	 List<Project> findByEmployeeId(Long employeeId);

	@Query("SELECT p FROM Project p WHERE p.employee.employeeId = :employeeId")
	List<Project> getProjectsByEmployeeId(@Param("employeeId") Long employeeId);

}
