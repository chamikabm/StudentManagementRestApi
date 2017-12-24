package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.LecturerEntity;
import com.student.management.rest.api.Model.Lecturer;
import com.student.management.rest.api.Repository.LecturerRepository;
import com.student.management.rest.api.Service.LecturerService;
import com.student.management.rest.api.Util.CustomErrorType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecturerServiceImpl implements LecturerService {

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

    @Override
    public Lecturer findById(Integer id) throws CustomErrorType{

        LecturerEntity lecturerEntity = this.lecturerRepository.findOne(id);

        if (lecturerEntity == null) {
            throw new CustomErrorType("Lecturer with id " + id + " not found");
        } else {
            return convertDaoToDto(lecturerEntity);
        }
    }

    @Override
    public boolean isLecturerExist(Lecturer lecturer) {
        return this.lecturerRepository.exists(lecturer.getId());
    }

    @Override
    public void addNewLecturer(Lecturer lecturer) {
        LecturerEntity lecturerEntity = convertDtoToDao(lecturer);
        this.lecturerRepository.save(lecturerEntity);
    }

    @Override
    public void updateLecturer(Lecturer currentLecturer) {

    }

    @Override
    public void deleteLecturerById(Integer id) {
        this.lecturerRepository.delete(id);
    }

    @Override
    public void deleteAllLecturers() {
        this.lecturerRepository.deleteAll();
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
