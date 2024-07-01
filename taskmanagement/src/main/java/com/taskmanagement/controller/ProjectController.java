package com.taskmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.entity.Project;
import com.taskmanagement.service.ProjectService;
import com.taskmanagement.util.ResponseStructure;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@GetMapping("/fetchall")
	public ResponseEntity<ResponseStructure<List<Project>>> findAllProjects() {
		return projectService.findAllProjects();
	}

	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<ResponseStructure<List<Project>>> getProjectsByEmployeeId(@PathVariable Long employeeId) {
		return projectService.getProjectsByEmployeeId(employeeId);
	}

	@PostMapping("/saveProject")
	public ResponseEntity<ResponseStructure<Project>> createProject(@RequestBody Project project) {
		return projectService.saveProject(project);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Project>> updateProject(@PathVariable Long id,
			@RequestBody Project project) {
		project.setProjectId(id);
		return projectService.updateProject(id, project);
	}
}
