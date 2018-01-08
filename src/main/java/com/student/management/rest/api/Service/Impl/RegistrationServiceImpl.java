package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.RegistrationEntity;
import com.student.management.rest.api.Manager.RegistrationManager;
import com.student.management.rest.api.Model.Registration;
import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Repository.RegistrationRepository;
import com.student.management.rest.api.Service.RegistrationService;
import com.student.management.rest.api.Util.CustomErrorType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }


    @Override
    public Registration registerNewStudent(Student student) {
        LOGGER.info("SMAPI - Registration - Service- registerNewStudent method invoked.");

        RegistrationEntity newRegistration = new RegistrationEntity();
        newRegistration.setStudentId(student.getId());
        newRegistration.setRegisteredDate(new Date());


        LOGGER.info("SMAPI - Registration - Service- registerNewStudent method processed.");
        return convertRegisterDaoToDto(this.registrationRepository.save(newRegistration));
    }

    @Override
    public Registration getStudentRegistration(Student student) {

        LOGGER.info("SMAPI - Registration - Service- getStudentRegistration method invoked.");

        LOGGER.info("SMAPI - Registration - Service- getStudentRegistration method processed.");
        return convertRegisterDaoToDto(this.registrationRepository.findByStudentId(student.getId()));
    }

    @Override
    public Boolean removeStudentRegistration(Integer studentId) {
        LOGGER.info("SMAPI - Registration - Service- removeStudentRegistration method invoked.");

        this.registrationRepository.deleteByStudentId(studentId);

        LOGGER.info("SMAPI - Registration - Service- removeStudentRegistration method processed.");

        return Boolean.TRUE;
    }

    @Override
    public Boolean isRegisteredStudent(Integer studentId) {
        LOGGER.info("SMAPI - Registration - Service- registerNewStudent method invoked.");

        Registration registration =  convertRegisterDaoToDto(this.registrationRepository.findByStudentId(studentId));

        LOGGER.info("SMAPI - Registration - Service- registerNewStudent method processed.");

        if (registration != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public List<Registration> findAllRegistrations() {
        LOGGER.info("SMAPI - Registration - Service- findAllRegistrations method invoked.");

        List<RegistrationEntity> registrationEntities = new ArrayList<>();
        this.registrationRepository.findAll().forEach(registrationEntities::add);

        LOGGER.info("SMAPI - Registration - Service- findAllRegistrations method processed.");

        return  registrationEntities.stream()
                .map(this::convertRegisterDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Registration findById(Integer id) throws CustomErrorType {
        LOGGER.info("SMAPI - Registration - Service- findById method invoked.");

        RegistrationEntity registrationEntity = this.registrationRepository.findOne(id);

        LOGGER.info("SMAPI - Registration - Service- findById method processed.");
        if (registrationEntity == null) {
            throw new CustomErrorType("Registration with id " + id + " not found");
        } else {
            return convertRegisterDaoToDto(registrationEntity);
        }
    }

    @Override
    public Registration addNewRegistration(Registration registration) throws CustomErrorType {
        LOGGER.info("SMAPI - Registration - Service- addNewRegistration method invoked.");

        RegistrationManager registrationManager = new RegistrationManager();

        LOGGER.info("SMAPI - Registration - Service- addNewRegistration method processed.");

        if(registrationManager.isValidRegistration(registration)) {
            RegistrationEntity registrationEntity = convertRegisterDtoToDao(registration);
            return convertRegisterDaoToDto(this.registrationRepository.save(registrationEntity));
        } else {
            throw new CustomErrorType("Invalid Registration Object Found!");
        }
    }

    @Override
    public void updateRegistration(Registration registration) {

    }

    @Override
    public void deleteRegistrationById(Integer id) {
        LOGGER.info("SMAPI - Registration - Service- deleteRegistrationById method invoked.");

        LOGGER.info("SMAPI - Registration - Service- deleteRegistrationById method processed.");

        this.registrationRepository.delete(id);
    }

    @Override
    public void deleteAllRegistrations() {
        LOGGER.info("SMAPI - Registration - Service- deleteAllRegistrations method invoked.");

        LOGGER.info("SMAPI - Registration - Service- deleteAllRegistrations method processed.");

        this.registrationRepository.deleteAll();
    }

    private Registration convertRegisterDaoToDto(RegistrationEntity registrationEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(registrationEntity, Registration.class);
    }

    private RegistrationEntity convertRegisterDtoToDao(Registration registration) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(registration, RegistrationEntity.class);
    }
}
