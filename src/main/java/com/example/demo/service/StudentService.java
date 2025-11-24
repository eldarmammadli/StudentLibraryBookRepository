package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Library;
import com.example.demo.model.Student;
import com.example.demo.payload.StudentPayload;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final LibraryRepository libraryRepository;
    private final List<Student> students = new ArrayList<>();

    @Autowired
    public StudentService(StudentRepository studentRepository, LibraryRepository libraryRepository) {
        this.studentRepository = studentRepository;
        this.libraryRepository = libraryRepository;
    }

    public List<StudentDTO> getStudents(String name) {
//        if (name != null && !name.isEmpty()) {
//            return studentRepository.findByNameLikeIgnoreCase("%" + name + "%");
//        }
        return studentRepository.findAll().stream().map(student -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
            studentDTO.setAge(student.getAge());
            studentDTO.setGender(student.getGender());
            studentDTO.setBirthDate(student.getBirthDate());
            return studentDTO;
        }).collect(Collectors.toList());
    }

    public Student getById(int id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public void create(StudentPayload studentPayload) {
        Student student = new Student();
        student.setName(studentPayload.getName());
        student.setAge(studentPayload.getAge());
        student.setGender(studentPayload.getGender());
        student.setBirthDate(studentPayload.getBirthDate());
        Library library = libraryRepository.findById(studentPayload.getLibraryId()).orElseThrow(() -> new RuntimeException("Library not found with id: " + studentPayload.getLibraryId()));
        student.getLibraries().add(library);
        library.getStudents().add(student);
        studentRepository.save(student);
    }

    public void edit(int id, Student s) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        student.setName(s.getName());
        studentRepository.save(student);
    }

    public void delete(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentRepository.delete(student);
    }
}
