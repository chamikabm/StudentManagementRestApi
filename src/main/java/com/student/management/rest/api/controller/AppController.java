package com.student.management.rest.api.controller;

import com.student.management.rest.api.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final StudentService studentService;

    @Autowired
    public AppController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/welcome")
    public ResponseEntity<String> welcome() {
        String welcomeMessage = studentService.getWelcomeMessage();

        return new ResponseEntity<>(welcomeMessage, HttpStatus.OK);
    }
}
