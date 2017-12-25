package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Exam;

public class ExamManager {

    public Boolean isValidExam(Exam exam) {
        if (exam.getName() == null || exam.getCourseId() == null ||
                exam.getDepartmentId() == null) {

            return Boolean.FALSE;
        } else {

            return Boolean.TRUE;
        }
    }
}
