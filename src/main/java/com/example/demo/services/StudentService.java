package com.example.demo.services;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(long id) {
        Optional<Student> student = studentRepo.findById(id);
        return student.isPresent() ? student.get() : null;
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public void deleteStudent(long id) {
        studentRepo.deleteById(id);
    }
}
