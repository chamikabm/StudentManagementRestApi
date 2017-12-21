package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> findAllExams();
}
