package com.student.management.rest.api.Manager.Impl;

import com.student.management.rest.api.Manager.DepartmentManager;
import com.student.management.rest.api.Model.Department;

public class DepartmentManagerImpl implements DepartmentManager {

    @Override
    public Boolean isValidDepartment(Department department) {
        if (department.getName() == null || department.getDepartmentHeadId() == null) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
