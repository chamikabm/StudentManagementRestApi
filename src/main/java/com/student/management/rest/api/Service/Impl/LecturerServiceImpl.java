package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.LecturerEntity;
import com.student.management.rest.api.Manager.LecturerManager;
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
        LOGGER.info("Lecturer - Service- findAllLecturers method invoked.");

        List<LecturerEntity> lecturerEntities = new ArrayList<>();
        this.lecturerRepository.findAll().forEach(lecturerEntities::add);

        LOGGER.info("Lecturer - Service- findAllLecturers method processed.");
        return  lecturerEntities.stream()
                .map(this::convertDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Lecturer findById(Integer id) throws CustomErrorType {
        LOGGER.info("Lecturer - Service- findById method invoked.");

        LecturerEntity lecturerEntity = this.lecturerRepository.findOne(id);

        if (lecturerEntity == null) {
            LOGGER.info("Lecturer - Service- findById method processed.");

            throw new CustomErrorType("Lecturer with id " + id + " not found");
        } else {
            LOGGER.info("Lecturer - Service- findById method processed.");

            return convertDaoToDto(lecturerEntity);
        }
    }

    @Override
    public boolean isLecturerExist(Lecturer lecturer) {
        LOGGER.info("Lecturer - Service- isLecturerExist method invoked.");

        LOGGER.info("Lecturer - Service- isLecturerExist method processed.");

        return this.lecturerRepository.exists(lecturer.getId());
    }

    @Override
    public void addNewLecturer(Lecturer lecturer) throws CustomErrorType {
        LOGGER.info("Lecturer - Service- addNewLecturer method invoked.");

        LecturerManager lecturerManager = new LecturerManager();

        if (lecturerManager.isValidLecturer(lecturer)) {
            LecturerEntity lecturerEntity = convertDtoToDao(lecturer);
            this.lecturerRepository.save(lecturerEntity);
            LOGGER.info("Lecturer - Service- addNewLecturer method processed.");

        } else {
            LOGGER.info("Lecturer - Service- addNewLecturer method processed.");

            throw new CustomErrorType("Invalid Lecturer Object Found!");
        }
    }

    @Override
    public void updateLecturer(Lecturer currentLecturer) {

    }

    @Override
    public void deleteLecturerById(Integer id) {
        LOGGER.info("Lecturer - Service- deleteLecturerById method invoked.");

        this.lecturerRepository.delete(id);

        LOGGER.info("Lecturer - Service- deleteLecturerById method processed.");
    }

    @Override
    public void deleteAllLecturers() {
        LOGGER.info("Lecturer - Service- deleteAllLecturers method invoked.");

        this.lecturerRepository.deleteAll();

        LOGGER.info("Lecturer - Service- deleteAllLecturers method processed.");
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
