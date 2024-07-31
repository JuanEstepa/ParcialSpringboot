package com.example.demo.controllers;

import com.example.demo.models.Teacher;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<Object> getAllTeachers(){
        try {
            List<Teacher> teachers = teacherService.getAllTeachers();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, teachers );
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getTeacherById(@PathVariable("id") int id){
        try {
            Teacher teacher = teacherService.getTeacherById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, teacher );
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createTeacher(@RequestBody Teacher teacher){
        try {
            Teacher reseult = teacherService.addTeacher(teacher);
            return ResponseHandler.generateResponse("Success", HttpStatus.CREATED, reseult );
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable long id){
        try {
            Teacher teacher = teacherService.getTeacherById(id);
            if(teacher != null){
                teacherService.deleteTeacher(id);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, teacher);
            }else {
                return ResponseHandler.generateResponse("Error", HttpStatus.NOT_FOUND, id + "Not found");
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
