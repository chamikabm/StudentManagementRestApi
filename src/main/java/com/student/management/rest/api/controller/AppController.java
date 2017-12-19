package com.student.management.rest.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AppController {

    @RequestMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
