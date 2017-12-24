package com.student.management.rest.api.Controller;

import com.student.management.rest.api.Model.Department;
import com.student.management.rest.api.Service.DepartmentService;
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

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    // -------------------Retrieve All Departments--------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Department>> listAllDepartments() {
        LOGGER.info("Department - Controller- listAllDepartments request received.");
        List<Department> departments = departmentService.findAllDepartments();

        if (departments.isEmpty()) {
            LOGGER.info("Department - Controller- listAllDepartments request processed.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        LOGGER.info("Department - Controller- listAllDepartments request processed.");
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // -------------------Retrieve Single Department------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartment(@PathVariable("id") Integer id) {
        LOGGER.info("Department - Controller- getDepartment request received.");

        Department department;

        try {
            department = departmentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("Department - Controller- getDepartment request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Department - Controller- getDepartment request processed.");
        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }

    // -------------------Create a Department-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createDepartment(@RequestBody Department department, UriComponentsBuilder ucBuilder) {
        LOGGER.info("Department - Controller- createDepartment request received.");

        departmentService.addNewDepartment(department);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/department/{id}").buildAndExpand(department.getId()).toUri());

        LOGGER.info("Department - Controller- createDepartment request processed.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Department ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Integer id, @RequestBody Department department) {
        LOGGER.info("Department - Controller- updateDepartment request received.");

        Department currentDepartment;

        try {
            currentDepartment = departmentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("Department - Controller- updateDepartment request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        currentDepartment.setName(department.getName());
        currentDepartment.setDepartmentHeadId(department.getDepartmentHeadId());

        departmentService.updateDepartment(currentDepartment);

        LOGGER.info("Department - Controller- updateDepartment request processed.");
        return new ResponseEntity<>(currentDepartment, HttpStatus.OK);
    }

    // ------------------- Delete a Department-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Integer id) {
        LOGGER.info("Department - Controller- deleteDepartment request received.");

        try {
            departmentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("Department - Controller- deleteDepartment request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        departmentService.deleteDepartmentById(id);

        LOGGER.info("Department - Controller- deleteDepartment request processed.");
        return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Departments-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Department> deleteAllDepartments() {
        LOGGER.info("Department - Controller- deleteAllDepartments request received.");

        departmentService.deleteAllDepartments();

        LOGGER.info("Department - Controller- deleteAllDepartments request processed.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
