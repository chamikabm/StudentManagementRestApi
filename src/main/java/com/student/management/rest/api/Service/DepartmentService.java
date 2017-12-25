package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Department;
import com.student.management.rest.api.Util.CustomErrorType;

import java.util.List;

public interface DepartmentService {

    Department findById(Integer id) throws CustomErrorType;

    Department findByName(String departmentName);

    void addNewDepartment(Department department) throws CustomErrorType;

    void updateDepartment(Department department);

    void deleteDepartmentById(Integer id);

    List<Department> findAllDepartments();

    boolean isDepartmentExist(Department department);

    void deleteAllDepartments();
}
