package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.RegistrationEntity;
import com.student.management.rest.api.Model.Registration;
import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Repository.RegistrationRepository;
import com.student.management.rest.api.Service.RegistrationService;
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
        return null;
    }

    @Override
    public Boolean removeStudentRegistration(Student student) {
        return null;
    }

    @Override
    public Boolean isRegisteredStudent(Integer studentId) {
        return null;
    }

    @Override
    public List<Registration> findAllRegistrations() {
        List<RegistrationEntity> registrationEntities = new ArrayList<>();
        this.registrationRepository.findAll().forEach(registrationEntities::add);

        return  registrationEntities.stream()
                .map(this::convertRegisterDaoToDto)
                .collect(Collectors.toList());
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
