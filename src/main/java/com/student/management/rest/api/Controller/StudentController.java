package com.student.management.rest.api.Controller;

import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Service.StudentService;
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
@RequestMapping("student")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // -------------------Retrieve All Students--------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllStudents() {

        List<Student> students = studentService.findAllStudents();

        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // -------------------Retrieve Single Student------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudent(@PathVariable("id") Integer id) {

        Student student = studentService.findById(id);
        if (student == null) {
            //logger.error("User with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    // -------------------Create a Student-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {

        if (studentService.isStudentExist(student)) {
            //logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Student with name " +
                    student.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        studentService.saveStudent(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/student/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Student ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {

        Student currentStudent = studentService.findById(id);

        if (currentStudent == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Student with id " +
                    "" + id + " not found."), HttpStatus.NOT_FOUND);
        }

        currentStudent.setName(student.getName());
        currentStudent.setAge(student.getAge());
        currentStudent.setDepartment(student.getDepartment());
        currentStudent.setAddress(student.getAddress());
        currentStudent.setContactNo(student.getContactNo());
        currentStudent.setEmail(student.getEmail());
        currentStudent.setSemester(student.getSemester());

        studentService.updateStudent(currentStudent);
        return new ResponseEntity<>(currentStudent, HttpStatus.OK);
    }

    // ------------------- Delete a Student-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Integer id) {

        Student student = studentService.findById(id);

        if (student == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. " +
                    "Student with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        studentService.deleteStudentById(id);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Students-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteAllUsers() {

        studentService.deleteAllStudents();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
