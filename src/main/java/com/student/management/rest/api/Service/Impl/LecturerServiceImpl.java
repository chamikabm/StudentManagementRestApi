package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.LecturerEntity;
import com.student.management.rest.api.Model.Lecturer;
import com.student.management.rest.api.Repository.LecturerRepository;
import com.student.management.rest.api.Service.LecturerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecturerServiceImpl implements LecturerService{

    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerServiceImpl.class);

    private final LecturerRepository lecturerRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public List<Lecturer> findAllLecturers() {
        List<LecturerEntity> lecturerEntities = new ArrayList<>();
        this.lecturerRepository.findAll().forEach(lecturerEntities::add);

        return  lecturerEntities.stream()
                .map(this::convertDaoToDto)
                .collect(Collectors.toList());
    }

    private Lecturer convertDaoToDto(LecturerEntity lecturerEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(lecturerEntity, Lecturer.class);
    }

    private LecturerEntity convertDtoToDao(Lecturer exam) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(exam, LecturerEntity.class);
    }
}
