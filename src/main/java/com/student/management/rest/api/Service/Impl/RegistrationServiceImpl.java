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

        RegistrationEntity newRegistration = new RegistrationEntity();
        newRegistration.setStudentId(student.getId());
        newRegistration.setRegisteredDate(new Date());


        return convertRegisterDaoToDto(this.registrationRepository.save(newRegistration));
    }

    @Override
    public Registration getStudentRegistration(Student student) {

        return convertRegisterDaoToDto(this.registrationRepository.findByStudentId(student.getId()));
    }

    @Override
    public Boolean removeStudentRegistration(Integer studentId) {
        this.registrationRepository.deleteByStudentId(studentId);

        return Boolean.TRUE;
    }

    @Override
    public Boolean isRegisteredStudent(Integer studentId) {
        Registration registration =  convertRegisterDaoToDto(this.registrationRepository.findByStudentId(studentId));

        if (registration != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public List<Registration> findAllRegistrations() {
        List<RegistrationEntity> registrationEntities = new ArrayList<>();
        this.registrationRepository.findAll().forEach(registrationEntities::add);

        return  registrationEntities.stream()
                .map(this::convertRegisterDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Registration findById(Integer id) throws CustomErrorType {

        RegistrationEntity registrationEntity = this.registrationRepository.findOne(id);
        if (registrationEntity == null) {
            throw new CustomErrorType("Registration with id " + id + " not found");
        } else {
            return convertRegisterDaoToDto(registrationEntity);
        }
    }

    @Override
    public Registration addNewRegistration(Registration registration) throws CustomErrorType {
        RegistrationManager registrationManager = new RegistrationManager();

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
        this.registrationRepository.delete(id);
    }

    @Override
    public void deleteAllRegistrations() {
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
