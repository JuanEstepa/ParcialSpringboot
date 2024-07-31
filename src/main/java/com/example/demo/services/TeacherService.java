package com.example.demo.services;

import com.example.demo.models.Teacher;
import com.example.demo.repositories.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(long id) {
        Optional<Teacher> teacher = teacherRepo.findById(id);
        return teacher.isPresent() ? teacher.get() : null;
    }

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    public void deleteTeacher(long id) {
        teacherRepo.deleteById(id);
    }
}
