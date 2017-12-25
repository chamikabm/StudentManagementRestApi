package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Student;

public class StudentManager {

    public Boolean isValidStudent(Student student) {
        if (student.getName() == null || student.getDepartment() == null
                || student.getSemester() == null) {

            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
