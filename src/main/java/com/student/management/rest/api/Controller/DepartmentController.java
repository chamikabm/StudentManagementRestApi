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
        List<Department> departments = departmentService.findAllDepartments();

        if (departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // -------------------Retrieve Single Department------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartment(@PathVariable("id") Integer id) {
        Department department = departmentService.findById(id);
        if (department == null) {
            return new ResponseEntity<>(new CustomErrorType("Department with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }

    // -------------------Create a Student-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createDepartment(@RequestBody Department department, UriComponentsBuilder ucBuilder) {

        if (departmentService.isDepartmentExist(department)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Department with name " +
                    department.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        departmentService.addNewDepartment(department);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/department/{id}").buildAndExpand(department.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Department ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Integer id, @RequestBody Department department) {

        Department currentDepartment = departmentService.findById(id);

        if (currentDepartment == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Department with id " +
                    "" + id + " not found."), HttpStatus.NOT_FOUND);
        }

        currentDepartment.setName(department.getName());
        currentDepartment.setDepartmentHeadId(department.getDepartmentHeadId());

        departmentService.updateDepartment(currentDepartment);
        return new ResponseEntity<>(currentDepartment, HttpStatus.OK);
    }

    // ------------------- Delete a Department-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Integer id) {

        Department department = departmentService.findById(id);

        if (department == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. " +
                    "Department with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Departments-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Department> deleteAllDepartments() {

        departmentService.deleteAllDepartments();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
