package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.payload.StudentPayload;
import com.example.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> get(@RequestParam(required = false) String name) {
        return studentService.getStudents(name);
    }

    @GetMapping("{id}")
    public Student getById(@PathVariable int id) {
        return studentService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody StudentPayload student) {
        studentService.create(student);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable int id, @RequestBody Student student) {
        studentService.edit(id, student);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) {
        studentService.delete(id);
    }
}
