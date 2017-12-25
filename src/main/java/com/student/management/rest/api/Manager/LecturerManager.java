package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Lecturer;

public class LecturerManager {

    public Boolean isValidLecturer(Lecturer lecturer) {
        if (lecturer.getName() == null || lecturer.getDepartmentId() == null) {

            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
