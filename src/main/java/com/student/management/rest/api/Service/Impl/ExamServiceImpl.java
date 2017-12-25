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
        List<ExamEntity> examEntities = new ArrayList<>();
        this.examRepository.findAll().forEach(examEntities::add);

        return  examEntities.stream()
                .map(this::convertDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Exam findById(Integer id) throws CustomErrorType{

        ExamEntity examEntity = this.examRepository.findOne(id);

        if (examEntity == null) {
            throw new CustomErrorType("Exam with id " + id + " not found");
        } else {
            return convertDaoToDto(examEntity);
        }
    }

    @Override
    public boolean isExamExist(Exam exam) {
        return this.examRepository.exists(exam.getId());
    }

    @Override
    public void addNewExam(Exam exam) throws CustomErrorType {

        ExamManager examManager = new ExamManager();

        if(examManager.isValidExam(exam)) {
            ExamEntity examEntity = convertDtoToDao(exam);
            this.examRepository.save(examEntity);
        } else {
            throw new CustomErrorType("Invalid Exam Object Found!");
        }

    }

    @Override
    public void updateExam(Exam currentExam) {

    }

    @Override
    public void deleteExamById(Integer id) {
        this.examRepository.delete(id);
    }

    @Override
    public void deleteAllExams() {
        this.examRepository.deleteAll();
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
