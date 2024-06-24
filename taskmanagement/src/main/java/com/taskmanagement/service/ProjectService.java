package com.taskmanagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.entity.Project;
import com.taskmanagement.repository.ProjectRepository;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getProjectsByEmployeeId(Long employeeId) {
        return projectRepository.findByEmployeeId(employeeId);
    }

    public Project saveProject(Project project) {
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());
        return projectRepository.save(project);
    }

    public Project updateProject(Project project) {
        project.setUpdatedAt(LocalDateTime.now());
        return projectRepository.save(project);
    }

}
