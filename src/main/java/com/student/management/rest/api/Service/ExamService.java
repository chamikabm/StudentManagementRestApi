package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Exam;

import java.util.List;

public interface ExamService {

    List<Exam> findAllExams();

    Exam findById(Integer id);

    boolean isExamExist(Exam exam);

    void addNewExam(Exam exam);

    void updateExam(Exam currentExam);

    void deleteExamById(Integer id);

    void deleteAllExams();
}
