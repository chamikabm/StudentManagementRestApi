package com.student.management.rest.api.Manager.Impl;

import com.student.management.rest.api.Manager.LecturerManager;
import com.student.management.rest.api.Model.Lecturer;

public class LecturerManagerImpl implements LecturerManager {

    @Override
    public Boolean isValidLecturer(Lecturer lecturer) {
        if (lecturer.getName() == null || lecturer.getDepartmentId() == null) {

            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
