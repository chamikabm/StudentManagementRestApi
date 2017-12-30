package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentManager.class);

    public Boolean isValidStudent(Student student) {

        LOGGER.info("Student - Manager- isValidStudent method invoked.");

        LOGGER.info("Student - Manager- isValidStudent method processed.");

        if (student.getName() == null || student.getDepartment() == null
                || student.getSemester() == null) {

            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
