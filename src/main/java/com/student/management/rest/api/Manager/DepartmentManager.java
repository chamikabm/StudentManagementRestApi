package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Department;

public class DepartmentManager {

    public Boolean isValidDepartment(Department department) {
        if (department.getName() == null || department.getDepartmentHeadId() == null) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
