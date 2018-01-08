package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.ExamEntity;
import com.student.management.rest.api.Manager.ExamManager;
import com.student.management.rest.api.Model.Exam;
import com.student.management.rest.api.Repository.ExamRepository;
import com.student.management.rest.api.Service.ExamService;
import com.student.management.rest.api.Util.CustomErrorType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExamServiceImpl.class);

    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public List<Exam> findAllExams() {
        LOGGER.info("SMAPI - Exam - Service- findAllExams method invoked.");

        List<ExamEntity> examEntities = new ArrayList<>();
        this.examRepository.findAll().forEach(examEntities::add);

        LOGGER.info("SMAPI - Exam - Service- findAllExams method processed.");
        return  examEntities.stream()
                .map(this::convertDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Exam findById(Integer id) throws CustomErrorType{
        LOGGER.info("SMAPI - Exam - Service- findById method invoked.");

        ExamEntity examEntity = this.examRepository.findOne(id);

        if (examEntity == null) {
            LOGGER.info("SMAPI - Exam - Service- findById method processed.");
            throw new CustomErrorType("Exam with id " + id + " not found");
        } else {
            LOGGER.info("SMAPI - Exam - Service- findById method processed.");
            return convertDaoToDto(examEntity);
        }
    }

    @Override
    public boolean isExamExist(Exam exam) {
        LOGGER.info("SMAPI - Exam - Service- isExamExist method invoked.");

        LOGGER.info("SMAPI - Exam - Service- isExamExist method processed.");
        return this.examRepository.exists(exam.getId());
    }

    @Override
    public void addNewExam(Exam exam) throws CustomErrorType {
        LOGGER.info("SMAPI - Exam - Service- addNewExam method invoked.");

        ExamManager examManager = new ExamManager();

        if(examManager.isValidExam(exam)) {
            ExamEntity examEntity = convertDtoToDao(exam);
            this.examRepository.save(examEntity);
            LOGGER.info("SMAPI - Exam - Service- addNewExam method processed.");
        } else {
            LOGGER.info("SMAPI - Exam - Service- addNewExam method processed.");
            throw new CustomErrorType("Invalid Exam Object Found!");
        }

    }

    @Override
    public void updateExam(Exam currentExam) {

    }

    @Override
    public void deleteExamById(Integer id) {
        LOGGER.info("SMAPI - Exam - Service- deleteExamById method invoked.");

        this.examRepository.delete(id);

        LOGGER.info("SMAPI - Exam - Service- deleteExamById method processed.");
    }

    @Override
    public void deleteAllExams() {
        LOGGER.info("SMAPI - Exam - Service- deleteAllExams method invoked.");

        this.examRepository.deleteAll();

        LOGGER.info("SMAPI - Exam - Service- deleteAllExams method processed.");
    }

    private Exam convertDaoToDto(ExamEntity examEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(examEntity, Exam.class);
    }

    private ExamEntity convertDtoToDao(Exam exam) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(exam, ExamEntity.class);
    }
}
