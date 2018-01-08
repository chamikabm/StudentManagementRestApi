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

import java.util.Date;
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
        LOGGER.info("SMAPI - Exam - Controller- listAllExams request received.");

        List<Exam> exams = examService.findAllExams();

        if (exams.isEmpty()) {
            LOGGER.info("SMAPI - Exam - Controller- listAllExams request processed.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        LOGGER.info("SMAPI - Exam - Controller- listAllExams request processed.");
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    // -------------------Retrieve Single Exam------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getExam(@PathVariable("id") Integer id) {
        LOGGER.info("SMAPI - Exam - Controller- getExam request received.");

        Exam exam;

        try {
            exam = examService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Exam - Controller- getExam request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        LOGGER.info("SMAPI - Exam - Controller- getExam request processed.");
        return new ResponseEntity<Exam>(exam, HttpStatus.OK);
    }

    // -------------------Create a Exam-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createExam(@RequestBody Exam exam, UriComponentsBuilder ucBuilder) {
        LOGGER.info("SMAPI - Exam - Controller- createExam request received.");

        exam.setDate(new Date());

        try {
            examService.addNewExam(exam);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Exam - Controller- createExam request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/exam/{id}").buildAndExpand(exam.getId()).toUri());

        LOGGER.info("SMAPI - Exam - Controller- createExam request processed.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Exam ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateExam(@PathVariable("id") Integer id, @RequestBody Exam exam) {
        LOGGER.info("SMAPI - Exam - Controller- updateExam request received.");

        Exam currentExam;

        try {
            currentExam = examService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Exam - Controller- updateExam request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        currentExam.setName(exam.getName());
        currentExam.setCourseId(exam.getCourseId());
        currentExam.setDate(exam.getDate());
        currentExam.setDepartmentId(exam.getDepartmentId());

        examService.updateExam(currentExam);

        LOGGER.info("SMAPI - Exam - Controller- updateExam request processed.");
        return new ResponseEntity<>(currentExam, HttpStatus.OK);
    }

    // ------------------- Delete a Exam-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExam(@PathVariable("id") Integer id) {
        LOGGER.info("SMAPI - Exam - Controller- deleteExam request received.");

        try {
            examService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Exam - Controller- deleteExam request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        examService.deleteExamById(id);

        LOGGER.info("SMAPI - Exam - Controller- deleteExam request processed.");
        return new ResponseEntity<Exam>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Exams-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Exam> deleteAllExams() {
        LOGGER.info("SMAPI - Exam - Controller- deleteAllExams request received.");

        examService.deleteAllExams();

        LOGGER.info("SMAPI - Exam - Controller- deleteAllExams request processed.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

