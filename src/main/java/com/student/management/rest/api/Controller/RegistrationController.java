package com.student.management.rest.api.Controller;

import com.student.management.rest.api.Model.Registration;
import com.student.management.rest.api.Service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("register")
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // -------------------Retrieve All Registrations--------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Registration>> listAllRegistrations() {
        LOGGER.info("Register - Controller- listAllRegistrations request received.");

        List<Registration> registrations = registrationService.findAllRegistrations();

        if (registrations.isEmpty()) {
            LOGGER.info("Register - Controller- listAllRegistrations request processed.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        LOGGER.info("Register - Controller- listAllRegistrations request processed.");
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }
}
