package com.taskmanagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taskmanagement.entity.Project;
import com.taskmanagement.repository.ProjectRepository;
import com.taskmanagement.util.ResponseStructure;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

//    find the list of project by employee id

	public ResponseEntity<ResponseStructure<List<Project>>> getProjectsByEmployeeId(Long employeeId) {

		Project emp = projectRepository.findByEmployeeId(employeeId);
		ResponseStructure<List<Project>> structure = new ResponseStructure<List<Project>>();
		if (emp != null) {
			structure.setMessage("Projects Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(projectRepository.getProjectsByEmployeeId(employeeId));

			return new ResponseEntity<ResponseStructure<List<Project>>>(structure, HttpStatus.FOUND);
		} else {

			structure.setMessage("Employee Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<List<Project>>>(structure, HttpStatus.NOT_FOUND);
		}
	}

//    for saving the project

	public ResponseEntity<ResponseStructure<Project>> saveProject(Project project) {
		project.setCreatedAt(LocalDateTime.now());
		project.setUpdatedAt(LocalDateTime.now());
		ResponseStructure<Project> structure = new ResponseStructure<Project>();
		structure.setMessage("Employee saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(projectRepository.save(project));
		return new ResponseEntity<ResponseStructure<Project>>(structure, HttpStatus.CREATED);
	}

//    for updating the project

	public ResponseEntity<ResponseStructure<Project>> updateProject(Long projectId, Project project) {
		ResponseStructure<Project> structure = new ResponseStructure<Project>();
		Project project2 = projectRepository.findByProjectId(projectId);
		if (project2 != null) {
			project.setUpdatedAt(LocalDateTime.now());
			projectRepository.save(project);
			structure.setMessage("Project  Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(project);
			return new ResponseEntity<ResponseStructure<Project>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("Project  Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(project);
			return new ResponseEntity<ResponseStructure<Project>>(structure, HttpStatus.NOT_FOUND);
		}

	}

//    find all projects 
	public ResponseEntity<ResponseStructure<List<Project>>> findAllProjects() {
		List<Project> list = projectRepository.findAllProjects();
		ResponseStructure<List<Project>> structure = new ResponseStructure<List<Project>>();
		if (list.isEmpty()) {
			structure.setMessage("Projects Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Project>>>(structure, HttpStatus.NOT_FOUND);
		} else {

			structure.setMessage("Projects  Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Project>>>(structure, HttpStatus.FOUND);
		}
	}

}
