package com.student.management.rest.api.Controller;


import com.student.management.rest.api.Model.Exam;
import com.student.management.rest.api.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("exam")
public class ExamController {

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    // -------------------Retrieve All Exams--------------------------------------------

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Exam>> listAllExams() {
        List<Exam> exams = examService.findAllExams();

        if (exams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

}

