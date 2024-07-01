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
		List<Project> projects = projectRepository.getProjectsByEmployeeId(employeeId);
        ResponseStructure<List<Project>> structure = new ResponseStructure<>();
        if (!projects.isEmpty()) {
            structure.setMessage("Projects Found successfully");
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setData(projects);
            return new ResponseEntity<>(structure, HttpStatus.FOUND);
        } else {
            structure.setMessage("Employee Not Found");
            structure.setStatus(HttpStatus.NOT_FOUND.value());
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }


	}

//    for saving the project

	public ResponseEntity<ResponseStructure<Project>> saveProject(Project project) {
	    project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());
        ResponseStructure<Project> structure = new ResponseStructure<>();
        structure.setMessage("Project saved successfully");
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setData(projectRepository.save(project));
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

//    for updating the project

	   public ResponseEntity<ResponseStructure<Project>> updateProject(Long projectId, Project project) {
	        ResponseStructure<Project> structure = new ResponseStructure<>();
	        Project existingProject = projectRepository.findByProjectId(projectId);
	        if (existingProject != null) {
	            project.setUpdatedAt(LocalDateTime.now());
	            projectRepository.save(project);
	            structure.setMessage("Project updated successfully");
	            structure.setStatus(HttpStatus.OK.value());
	            structure.setData(project);
	            return new ResponseEntity<>(structure, HttpStatus.OK);
	        } else {
	            structure.setMessage("Project not found");
	            structure.setStatus(HttpStatus.NOT_FOUND.value());
	            structure.setData(project);
	            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	        }
	    }

//    find all projects 
	    public ResponseEntity<ResponseStructure<List<Project>>> findAllProjects() {
	        List<Project> list = projectRepository.findAll();
	        ResponseStructure<List<Project>> structure = new ResponseStructure<>();
	        if (list.isEmpty()) {
	            structure.setMessage("Projects Not Found");
	            structure.setStatus(HttpStatus.NOT_FOUND.value());
	            structure.setData(list);
	            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	        } else {
	            structure.setMessage("Projects Found");
	            structure.setStatus(HttpStatus.FOUND.value());
	            structure.setData(list);
	            return new ResponseEntity<>(structure, HttpStatus.FOUND);
	        }
	    }

}
