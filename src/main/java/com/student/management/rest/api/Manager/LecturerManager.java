package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Lecturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LecturerManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerManager.class);

    public Boolean isValidLecturer(Lecturer lecturer) {

        LOGGER.info("SMAPI - Lecturer - Manager- isValidLecturer method invoked.");

        LOGGER.info("SMAPI - Lecturer - Manager- isValidLecturer method processed.");

        if (lecturer.getName() == null || lecturer.getDepartmentId() == null) {

            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
