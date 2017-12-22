package com.student.management.rest.api.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @RequestMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
