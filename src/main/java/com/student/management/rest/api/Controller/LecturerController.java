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
        List<Lecturer> lecturers = lecturerService.findAllLecturers();

        if (lecturers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<>(lecturers, HttpStatus.OK);
    }

    // -------------------Retrieve Single Lecturer------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLecturer(@PathVariable("id") Integer id) {

        Lecturer lecturer = lecturerService.findById(id);
        if (lecturer == null) {
            return new ResponseEntity<>(new CustomErrorType("Lecturer with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Lecturer>(lecturer, HttpStatus.OK);
    }

    // -------------------Create a Lecturer-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createLecturer(@RequestBody Lecturer lecturer, UriComponentsBuilder ucBuilder) {

        if (lecturerService.isLecturerExist(lecturer)) {
            //logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Lecturer with name " +
                    lecturer.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        lecturerService.addNewLecturer(lecturer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/lecturer/{id}").buildAndExpand(lecturer.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Lecturer ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateLecturer(@PathVariable("id") Integer id, @RequestBody Lecturer lecturer) {

        Lecturer currentLecturer = lecturerService.findById(id);

        if (currentLecturer == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Lecturer with id " +
                    "" + id + " not found."), HttpStatus.NOT_FOUND);
        }

        currentLecturer.setName(lecturer.getName());
        currentLecturer.setAge(lecturer.getAge());
        currentLecturer.setDepartmentId(lecturer.getDepartmentId());
        currentLecturer.setAddress(lecturer.getAddress());
        currentLecturer.setContactNo(lecturer.getContactNo());
        currentLecturer.setEmail(lecturer.getEmail());

        lecturerService.updateLecturer(currentLecturer);
        return new ResponseEntity<>(currentLecturer, HttpStatus.OK);
    }

    // ------------------- Delete a Lecturer-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteLecturer(@PathVariable("id") Integer id) {

        Lecturer lecturer = lecturerService.findById(id);

        if (lecturer == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. " +
                    "Lecturer with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        lecturerService.deleteLecturerById(id);
        return new ResponseEntity<Lecturer>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Lecturer-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Lecturer> deleteAllLecturers() {

        lecturerService.deleteAllLecturers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
