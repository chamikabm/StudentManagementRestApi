package com.student.management.rest.api.Controller;

import com.student.management.rest.api.Model.Department;
import com.student.management.rest.api.Model.Exam;
import com.student.management.rest.api.Model.Payment;
import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Service.*;
import com.student.management.rest.api.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;
    private final RegistrationService registrationService;
    private final DepartmentService departmentService;
    private final ExamService examService;
    private final PaymentService paymentService;

    @Autowired
    public StudentController(StudentService studentService, RegistrationService registrationService,
                             DepartmentService departmentService, ExamService examService,
                             PaymentService paymentService) {
        this.studentService = studentService;
        this.registrationService = registrationService;
        this.departmentService = departmentService;
        this.examService = examService;
        this.paymentService = paymentService;
    }

    // -------------------Retrieve All Students--------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllStudents() {
        LOGGER.info("SMAPI - Student - Controller- listAllStudents request received.");

        List<Student> students = studentService.findAllStudents();

        if (students.isEmpty()) {
            LOGGER.info("SMAPI - Student - Controller- listAllStudents request processed.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        LOGGER.info("SMAPI - Student - Controller- listAllStudents request processed.");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // -------------------Retrieve Single Student------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudent(@PathVariable("id") Integer id) {
        LOGGER.info("SMAPI - Student - Controller- getStudent request received.");

        Student student;

        try {
            student = studentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Student - Controller- getStudent request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        LOGGER.info("SMAPI - Student - Controller- getStudent request processed.");
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    // -------------------Create a Student-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<?> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
        LOGGER.info("SMAPI - Student - Controller- createStudent request received.");

        Student newStudent;
        boolean shouldUpdateAdditionalInfo = false;

        try {
            newStudent = studentService.saveStudent(student);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Student - Controller- createStudent request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        registrationService.registerNewStudent(newStudent);

        if (shouldUpdateAdditionalInfo) {

            Payment payment = new Payment();
            payment.setAmount(0.0);
            payment.setPaymentDate(new Date());
            Exam exam = new Exam();

            try {
                paymentService.addNewPayment(payment);
                examService.addNewExam(exam);
            } catch (CustomErrorType customError) {
                LOGGER.info("SMAPI - Student - Controller- createStudent request processed.");
                return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/student/{id}").buildAndExpand(student.getId()).toUri());

        LOGGER.info("SMAPI - Student - Controller- createStudent request processed.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Student ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
        LOGGER.info("SMAPI - Student - Controller- updateStudent request received.");

        Student currentStudent;

        try {
            currentStudent = studentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Student - Controller- updateStudent request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        currentStudent.setName(student.getName());
        currentStudent.setAge(student.getAge());

        try {
            if (departmentService.findById(student.getDepartment())  != null) {
                currentStudent.setDepartment(student.getDepartment());
            }
        } catch (CustomErrorType customErrorType) {
            LOGGER.error("SMAPI - Student - Controller- updateStudent department not found.");
        }

        currentStudent.setAddress(student.getAddress());
        currentStudent.setContactNo(student.getContactNo());
        currentStudent.setEmail(student.getEmail());
        currentStudent.setSemester(student.getSemester());

        studentService.updateStudent(currentStudent);

        LOGGER.info("SMAPI - Student - Controller- updateStudent request processed.");
        return new ResponseEntity<>(currentStudent, HttpStatus.OK);
    }

    // ------------------- Delete a Student-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Integer id) {
        LOGGER.info("SMAPI - Student - Controller- deleteStudent request received.");

        try {
            studentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Student - Controller- deleteStudent request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        studentService.deleteStudentById(id);
        registrationService.removeStudentRegistration(id);

        LOGGER.info("SMAPI - Student - Controller- deleteStudent request processed.");
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Students-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteAllUsers() {
        LOGGER.info("SMAPI - Student - Controller- deleteAllUsers request received.");

        studentService.deleteAllStudents();

        LOGGER.info("SMAPI - Student - Controller- deleteAllUsers request processed.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
