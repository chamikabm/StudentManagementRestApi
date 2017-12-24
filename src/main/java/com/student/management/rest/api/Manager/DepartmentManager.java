package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartmentManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentManager.class);

    public Boolean isValidDepartment(Department department) {
        if (department.getName() == null || department.getDepartmentHeadId() == null) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
