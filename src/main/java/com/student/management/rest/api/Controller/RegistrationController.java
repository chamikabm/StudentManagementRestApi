package com.student.management.rest.api.Controller;

import com.student.management.rest.api.Model.Registration;
import com.student.management.rest.api.Service.*;
import com.student.management.rest.api.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("register")
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private final RegistrationService registrationService;
    private final DepartmentService departmentService;
    private final PaymentService paymentService;
    private final StudentService studentService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, DepartmentService departmentService,
                                  PaymentService paymentService, StudentService studentService) {
        this.registrationService = registrationService;
        this.departmentService = departmentService;
        this.paymentService = paymentService;
        this.studentService = studentService;
    }

    // -------------------Retrieve All Registrations--------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Registration>> listAllRegistrations() {
        LOGGER.info("SMAPI - Register - Controller- listAllRegistrations request received.");

        List<Registration> registrations = registrationService.findAllRegistrations();

        if (registrations.isEmpty()) {
            LOGGER.info("SMAPI - Register - Controller- listAllRegistrations request processed.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        LOGGER.info("SMAPI - Register - Controller- listAllRegistrations request processed.");
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    // -------------------Retrieve Single Registration------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRegistration(@PathVariable("id") Integer id) {
        LOGGER.info("SMAPI - Registration - Controller- getRegistration request received.");

        Registration registration;

        try {
            registration = registrationService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Registration - Controller- getRegistration request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        LOGGER.info("SMAPI - Registration - Controller- getRegistration request processed.");
        return new ResponseEntity<Registration>(registration, HttpStatus.OK);
    }

    // -------------------Create a Registration-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createRegistration(@RequestBody Registration registration, UriComponentsBuilder ucBuilder) {
        LOGGER.info("SMAPI - Registration - Controller- createRegistration request received. data id : {} ", registration.getId());

        try {
            registrationService.addNewRegistration(registration);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Registration - Controller- createRegistration request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/registration/{id}").buildAndExpand(registration.getId()).toUri());

        LOGGER.info("SMAPI - Registration - Controller- createRegistration request processed.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Registration ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRegistration(@PathVariable("id") Integer id, @RequestBody Registration registration) {
        LOGGER.info("SMAPI - Registration - Controller- updateRegistration request received.");

        Registration currentRegistration;

        try {
            currentRegistration = registrationService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Registration - Controller- updateRegistration request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        if (registration.getRegisteredDate() != null) {
            currentRegistration.setRegisteredDate(registration.getRegisteredDate());
        }

        currentRegistration.setStudentId(registration.getStudentId());
        currentRegistration.setIsRegistered(registration.getIsRegistered());

        registrationService.updateRegistration(currentRegistration);

        LOGGER.info("SMAPI - Registration - Controller- updateRegistration request processed.");
        return new ResponseEntity<>(currentRegistration, HttpStatus.OK);
    }

    // ------------------- Delete a Registration-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRegistration(@PathVariable("id") Integer id) {
        LOGGER.info("SMAPI - Registration - Controller- deleteRegistration request received.");

        try {
            registrationService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Registration - Controller- deleteRegistration request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        registrationService.deleteRegistrationById(id);

        LOGGER.info("SMAPI - Registration - Controller- deleteRegistration request processed.");
        return new ResponseEntity<Registration>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Registrations-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Registration> deleteAllRegistrations() {
        LOGGER.info("SMAPI - Registration - Controller- deleteRegistration request received.");

        registrationService.deleteAllRegistrations();

        LOGGER.info("SMAPI - Registration - Controller- deleteRegistration request processed.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // -------------------Retrieve All Registrations--------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Registration>> listAllRegisteredStudentsFromDepartment() {
        LOGGER.info("SMAPI - Register - Controller- listAllRegisteredStudentsFromDepartment request received.");

        List<Registration> registrations = registrationService.findAllRegistrations();

        if (registrations.isEmpty()) {
            LOGGER.info("SMAPI - Register - Controller- listAllRegisteredStudentsFromDepartment request processed.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        } else {
            departmentService.findAllDepartments();
            paymentService.findAllPayments();
            studentService.findAllStudents();
        }

        LOGGER.info("SMAPI - Register - Controller- listAllRegisteredStudentsFromDepartment request processed.");
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }
}
