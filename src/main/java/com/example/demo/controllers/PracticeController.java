package com.example.demo.controllers;

import com.example.demo.models.Practice;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.services.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("practice")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @GetMapping
    public ResponseEntity<Object> getAllPractices() {
        try {
            List<Practice> practices = practiceService.getAllPractices();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, practices );
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPractice(@PathVariable Long id) {
        try {
            Practice practice = practiceService.getByIdPractice(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, practice );
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }

    @PostMapping
    public ResponseEntity<Object> addPractice(@RequestBody Practice practice) {
        try {
            Practice result = practiceService.addPractice(practice);
            return ResponseHandler.generateResponse("Success", HttpStatus.CREATED, result );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePractice(@PathVariable Long id) {
        try{
            Practice practice = practiceService.getByIdPractice(id);
            if(practice != null){
                practiceService.deletePractice(id);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, practice);
            }else {
                return ResponseHandler.generateResponse("Error", HttpStatus.NOT_FOUND, id + "Not found");
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }
}
