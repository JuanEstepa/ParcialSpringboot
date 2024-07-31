package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Object> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, students );
        }catch (Exception e) {
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable long id) {
        try {
            Student student = studentService.getStudentById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, student );
        }catch (Exception e) {
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }

    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        try {
            Student result = studentService.addStudent(student);
            return ResponseHandler.generateResponse("Success", HttpStatus.CREATED, result );
        }catch (Exception e) {
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable long id) {
        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                studentService.deleteStudent(id);
                return ResponseHandler.generateResponse("Success", HttpStatus.NO_CONTENT, student);
            }else {
                return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, id + "Not found");
            }
        }catch (Exception e) {
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }
}
