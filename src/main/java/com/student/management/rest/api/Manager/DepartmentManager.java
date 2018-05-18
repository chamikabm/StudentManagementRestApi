package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartmentManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentManager.class);

    public Boolean isValidDepartment(Department department) {

        LOGGER.info("SMAPI - Department - Manager- isValidDepartment method invoked.");

        LOGGER.info("SMAPI - Department - Manager- isValidDepartment method processed.");
        if (department.getName() == null || department.getDepartmentHeadId() == null) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public Boolean isValidDepartment(Integer departmentId) {

        LOGGER.info("SMAPI - Department - Manager- isValidDepartment method invoked.");

        LOGGER.info("SMAPI - Department - Manager- isValidDepartment method processed.");
        if (departmentId == null) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }


}
