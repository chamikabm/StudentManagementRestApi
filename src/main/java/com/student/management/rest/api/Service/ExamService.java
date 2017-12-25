package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Exam;
import com.student.management.rest.api.Util.CustomErrorType;

import java.util.List;

public interface ExamService {

    List<Exam> findAllExams();

    Exam findById(Integer id) throws CustomErrorType;

    boolean isExamExist(Exam exam);

    void addNewExam(Exam exam) throws CustomErrorType;

    void updateExam(Exam currentExam);

    void deleteExamById(Integer id);

    void deleteAllExams();
}
