package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.ExamEntity;
import com.student.management.rest.api.Model.Exam;
import com.student.management.rest.api.Repository.ExamRepository;
import com.student.management.rest.api.Service.ExamService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService{

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

    private Exam convertDaoToDto(ExamEntity examEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(examEntity, Exam.class);
    }

    private ExamEntity convertDtoToDao(Exam exam) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(exam, ExamEntity.class);
    }
}
