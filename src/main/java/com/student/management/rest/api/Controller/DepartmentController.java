package com.student.management.rest.api.Controller;

import com.student.management.rest.api.Model.Department;
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

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;
    private final ExamService examService;
    private final LecturerService lecturerService;
    private final PaymentService paymentService;
    private final StudentService studentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, ExamService examService,
                                LecturerService lecturerService, PaymentService paymentService,
                                StudentService studentService) {
        this.departmentService = departmentService;
        this.examService = examService;
        this.lecturerService = lecturerService;
        this.paymentService = paymentService;
        this.studentService = studentService;
    }


    // -------------------Retrieve All Departments--------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Department>> listAllDepartments() {
        LOGGER.info("SMAPI - Department - Controller- listAllDepartments request received.");
        List<Department> departments = departmentService.findAllDepartments();

        if (departments.isEmpty()) {
            LOGGER.info("SMAPI - Department - Controller- listAllDepartments request processed.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        LOGGER.info("SMAPI - Department - Controller- listAllDepartments request processed.");
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // -------------------Retrieve Single Department------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartment(@PathVariable("id") Integer id) {
        LOGGER.info("SMAPI - Department - Controller- getDepartment request received.");

        Department department;

        try {
            department = departmentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Department - Controller- getDepartment request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        LOGGER.info("SMAPI - Department - Controller- getDepartment request processed.");
        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }

    // -------------------Create a Department-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createDepartment(@RequestBody Department department, UriComponentsBuilder ucBuilder) {
        LOGGER.info("SMAPI - Department - Controller- createDepartment request received.");

        try {
            departmentService.addNewDepartment(department);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI -Department - Controller- createDepartment request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/department/{id}").buildAndExpand(department.getId()).toUri());

        LOGGER.info("SMAPI - Department - Controller- createDepartment request processed.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Department ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Integer id, @RequestBody Department department) {
        LOGGER.info("SMAPI - Department - Controller- updateDepartment request received.");

        Department currentDepartment;

        try {
            currentDepartment = departmentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Department - Controller- updateDepartment request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        currentDepartment.setName(department.getName());
        currentDepartment.setDepartmentHeadId(department.getDepartmentHeadId());

        departmentService.updateDepartment(currentDepartment);

        LOGGER.info("SMAPI - Department - Controller- updateDepartment request processed.");
        return new ResponseEntity<>(currentDepartment, HttpStatus.OK);
    }

    // ------------------- Delete a Department-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Integer id) {
        LOGGER.info("SMAPI - Department - Controller- deleteDepartment request received.");

        try {
            departmentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Department - Controller- deleteDepartment request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        departmentService.deleteDepartmentById(id);

        LOGGER.info("SMAPI - Department - Controller- deleteDepartment request processed.");
        return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Departments-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Department> deleteAllDepartments() {
        LOGGER.info("SMAPI - Department - Controller- deleteAllDepartments request received.");

        departmentService.deleteAllDepartments();

        LOGGER.info("SMAPI - Department - Controller- deleteAllDepartments request processed.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // -----Retrieve All Departments for Student has Made the Exam Payment------
    @RequestMapping(value = "/getAllExamPaymentMadeStudentDepartments", method = RequestMethod.GET)
    public ResponseEntity<List<Department>> listAllDepartmentsMadePayment() {
        LOGGER.info("SMAPI - Department - Controller- listAllDepartmentsMadePayment request received.");
        List<Department> departments = departmentService.findAllDepartments();

        if (departments.isEmpty()) {
            LOGGER.info("SMAPI - Department - Controller- listAllDepartmentsMadePayment request processed.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        } else {
            examService.findAllExams();
            studentService.findAllStudents();
            lecturerService.findAllLecturers();
            paymentService.findAllPayments();
        }

        LOGGER.info("SMAPI - Department - Controller- listAllDepartmentsMadePayment request processed.");
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}
