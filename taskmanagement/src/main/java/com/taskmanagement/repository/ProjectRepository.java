package com.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.entity.Project;
import com.taskmanagement.entity.Task;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	List<Project>findByEmployeeId(Long employeeId);


}



