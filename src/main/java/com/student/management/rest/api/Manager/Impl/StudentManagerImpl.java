package com.student.management.rest.api.Manager.Impl;

import com.student.management.rest.api.Manager.StudentManager;
import com.student.management.rest.api.Model.Student;

public class StudentManagerImpl implements StudentManager {

    @Override
    public Boolean isValidStudent(Student student) {
        if (student.getName() == null || student.getDepartment() == null
                || student.getSemester() == null) {

            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
