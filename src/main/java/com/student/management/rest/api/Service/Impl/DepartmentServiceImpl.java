package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Model.Department;
import com.student.management.rest.api.Service.DepartmentService;
import com.student.management.rest.api.Util.DepartmentType;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public Department findByType(DepartmentType departmentType) {
        return null;
    }

    @Override
    public void addNewDepartment(Department department) {

    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartmentById(Integer id) {

    }

    @Override
    public List<Department> findAllDepartments() {
        return null;
    }

    @Override
    public boolean isDepartmentExist(Department department) {
        return false;
    }
}
