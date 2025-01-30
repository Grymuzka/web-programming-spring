package com.example.webprogrammingspring.controller;

import com.example.webprogrammingspring.StudentService;
import com.example.webprogrammingspring.entity.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students/list";
    }

    @GetMapping("/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "students/form";
        }
        studentService.save(student);
        return "redirect:/students";
    }
}

