package com.example.webprogrammingspring.controller;

import com.example.webprogrammingspring.StudentService;
import com.example.webprogrammingspring.entity.Project;
import com.example.webprogrammingspring.service.ProjectService;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "projects/list";
    }

    @GetMapping("/add")
    public String showAddProjectForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("students", studentService.findAll());
        return "projects/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditProjectForm(@PathVariable Long id, Model model) throws NotFoundException {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        model.addAttribute("students", studentService.findAll());
        return "projects/form";
    }

    @PostMapping("/save")
    public String saveProject(@Valid Project project, BindingResult result) {
        if (result.hasErrors()) {
            return "projects/form";
        }
        projectService.save(project);
        return "redirect:/projects";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteById(id);
        return "redirect:/projects";
    }
}

