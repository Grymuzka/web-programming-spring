package com.example.webprogrammingspring.service;

import com.example.webprogrammingspring.entity.Project;
import com.example.webprogrammingspring.repository.ProjectRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) throws NotFoundException {
        return projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project not found"));
    }

    public void save(Project project) {
        projectRepository.save(project);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> findByStatus(Project.ProjectStatus status) {
        return projectRepository.findByStatus(status);
    }

    public List<Project> sortProjectsByStartDate() {
        return projectRepository.findAll(Sort.by(Sort.Direction.ASC, "startDate"));
    }
}

