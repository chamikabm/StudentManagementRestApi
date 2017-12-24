package com.student.management.rest.api.Manager.Impl;

import com.student.management.rest.api.Manager.ExamManager;
import com.student.management.rest.api.Model.Exam;

public class ExamManagerImpl implements ExamManager {

    @Override
    public Boolean isValidExam(Exam exam) {
        if (exam.getName() == null || exam.getCourseId() == null ||
                exam.getDepartmentId() == null) {

            return Boolean.FALSE;
        } else {

            return Boolean.TRUE;
        }
    }
}
