package com.student.management.rest.api.Controller;

import com.student.management.rest.api.Model.Lecturer;
import com.student.management.rest.api.Service.LecturerService;
import com.student.management.rest.api.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("lecturer")
public class LecturerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerController.class);

    private final LecturerService lecturerService;

    @Autowired
    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    // -------------------Retrieve All Lecturers--------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Lecturer>> listAllLecturers() {
        LOGGER.info("Lecturer - Controller- listAllLecturers request received.");

        List<Lecturer> lecturers = lecturerService.findAllLecturers();

        if (lecturers.isEmpty()) {
            LOGGER.info("Lecturer - Controller- listAllLecturers request processed.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        LOGGER.info("Lecturer - Controller- listAllLecturers request processed.");
        return new ResponseEntity<>(lecturers, HttpStatus.OK);
    }

    // -------------------Retrieve Single Lecturer------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLecturer(@PathVariable("id") Integer id) {
        LOGGER.info("Lecturer - Controller- getLecturer request received.");

        Lecturer lecturer;

        try {
            lecturer = lecturerService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("Lecturer - Controller- getLecturer request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Lecturer - Controller- getLecturer request processed.");
        return new ResponseEntity<Lecturer>(lecturer, HttpStatus.OK);
    }

    // -------------------Create a Lecturer-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createLecturer(@RequestBody Lecturer lecturer, UriComponentsBuilder ucBuilder) {
        LOGGER.info("Lecturer - Controller- createLecturer request received.");

        lecturerService.addNewLecturer(lecturer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/lecturer/{id}").buildAndExpand(lecturer.getId()).toUri());

        LOGGER.info("Lecturer - Controller- createLecturer request processed.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Lecturer ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateLecturer(@PathVariable("id") Integer id, @RequestBody Lecturer lecturer) {
        LOGGER.info("Lecturer - Controller- updateLecturer request received.");

        Lecturer currentLecturer;

        try {
            currentLecturer = lecturerService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("Lecturer - Controller- updateLecturer request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        currentLecturer.setName(lecturer.getName());
        currentLecturer.setAge(lecturer.getAge());
        currentLecturer.setDepartmentId(lecturer.getDepartmentId());
        currentLecturer.setAddress(lecturer.getAddress());
        currentLecturer.setContactNo(lecturer.getContactNo());
        currentLecturer.setEmail(lecturer.getEmail());

        lecturerService.updateLecturer(currentLecturer);

        LOGGER.info("Lecturer - Controller- updateLecturer request processed.");
        return new ResponseEntity<>(currentLecturer, HttpStatus.OK);
    }

    // ------------------- Delete a Lecturer-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteLecturer(@PathVariable("id") Integer id) {
        LOGGER.info("Lecturer - Controller- deleteLecturer request received.");

        try {
            lecturerService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("Lecturer - Controller- deleteLecturer request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        lecturerService.deleteLecturerById(id);

        LOGGER.info("Lecturer - Controller- deleteLecturer request processed.");
        return new ResponseEntity<Lecturer>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Lecturer-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Lecturer> deleteAllLecturers() {
        LOGGER.info("Lecturer - Controller- deleteAllLecturers request received.");

        lecturerService.deleteAllLecturers();

        LOGGER.info("Lecturer - Controller- deleteAllLecturers request processed.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
