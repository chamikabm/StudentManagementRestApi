package com.student.management.rest.api.Controller;


import com.student.management.rest.api.Model.Exam;
import com.student.management.rest.api.Service.ExamService;
import com.student.management.rest.api.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("exam")
public class ExamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExamController.class);

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

    // -------------------Retrieve Single Exam------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getExam(@PathVariable("id") Integer id) {
        Exam exam = examService.findById(id);
        if (exam == null) {
            return new ResponseEntity<>(new CustomErrorType("Exam with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Exam>(exam, HttpStatus.OK);
    }

    // -------------------Create a Exam-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createExam(@RequestBody Exam exam, UriComponentsBuilder ucBuilder) {

        if (examService.isExamExist(exam)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Exam with name " +
                    exam.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        examService.addNewExam(exam);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/exam/{id}").buildAndExpand(exam.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Exam ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateExam(@PathVariable("id") Integer id, @RequestBody Exam exam) {

        Exam currentExam = examService.findById(id);

        if (currentExam == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Department with id " +
                    "" + id + " not found."), HttpStatus.NOT_FOUND);
        }

        currentExam.setName(exam.getName());
        currentExam.setCourseId(exam.getCourseId());
        currentExam.setDate(exam.getDate());
        currentExam.setDepartmentId(exam.getDepartmentId());

        examService.updateExam(currentExam);
        return new ResponseEntity<>(currentExam, HttpStatus.OK);
    }

    // ------------------- Delete a Exam-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExam(@PathVariable("id") Integer id) {

        Exam exam = examService.findById(id);

        if (exam == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. " +
                    "Exam with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        examService.deleteExamById(id);
        return new ResponseEntity<Exam>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Exams-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Exam> deleteAllExams() {

        examService.deleteAllExams();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

