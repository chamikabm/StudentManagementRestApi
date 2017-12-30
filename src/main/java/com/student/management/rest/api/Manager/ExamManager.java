package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Exam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExamManager.class);

    public Boolean isValidExam(Exam exam) {

        LOGGER.info("Exam - Manager- isValidExam method invoked.");

        LOGGER.info("Exam - Manager- isValidExam method processed.");

        if (exam.getName() == null || exam.getCourseId() == null ||
                exam.getDepartmentId() == null) {

            return Boolean.FALSE;
        } else {

            return Boolean.TRUE;
        }
    }
}
