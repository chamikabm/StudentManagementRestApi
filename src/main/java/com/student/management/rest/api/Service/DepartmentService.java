package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Department;

import java.util.List;

public interface DepartmentService {

    Department findById(Integer id);

    Department findByName(String departmentName);

    void addNewDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartmentById(Integer id);

    List<Department> findAllDepartments();

    boolean isDepartmentExist(Department department);

    void deleteAllDepartments();
}
