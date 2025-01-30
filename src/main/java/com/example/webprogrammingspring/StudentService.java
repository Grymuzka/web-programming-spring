package com.example.webprogrammingspring;

import com.example.webprogrammingspring.entity.Student;
import com.example.webprogrammingspring.repository.StudentRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) throws NotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
