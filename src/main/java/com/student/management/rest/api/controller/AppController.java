package com.student.management.rest.api.controller;

import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Service.StudentService;
import com.student.management.rest.api.Util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

    // -------------------Retrieve All Students--------------------------------------------

    @RequestMapping(value = "/students/", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllStudents() {
        List<Student> students = studentService.findAllStudents();

        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // -------------------Retrieve Single Student------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudent(@PathVariable("id") Integer id) {
        //logger.info("Fetching User with id {}", id);
        Student student = studentService.findById(id);
        if (student == null) {
            //logger.error("User with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    // -------------------Create a Student-------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
        //logger.info("Creating User : {}", user);

        if (studentService.isStudentExist(student)) {
            //logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Student with name " +
                    student.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        studentService.saveStudent(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Student ------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
        //logger.info("Updating User with id {}", id);

        Student currentStudent = studentService.findById(id);

        if (currentStudent == null) {
            //logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Student with id " +
                    "" + id + " not found."), HttpStatus.NOT_FOUND);
        }

        currentStudent.setName(student.getName());
        currentStudent.setAge(student.getAge());
        currentStudent.setDepartment(student.getDepartment());

        studentService.updateStudent(currentStudent);

        return new ResponseEntity<>(currentStudent, HttpStatus.OK);
    }

    // ------------------- Delete a User-----------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Integer id) {
        //logger.info("Fetching & Deleting User with id {}", id);

        Student student = studentService.findById(id);

        if (student == null) {
            //logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. " +
                    "Student with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        studentService.deleteStudentById(id);

        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users-----------------------------

    @RequestMapping(value = "/users/", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteAllUsers() {
        //logger.info("Deleting All Users");

        studentService.deleteAllStudents();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
