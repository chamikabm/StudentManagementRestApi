package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Model.Lecturer;
import com.student.management.rest.api.Model.Payment;
import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public Boolean isValidStudent(Student student) {
        return null;
    }

    @Override
    public Boolean isValidStudent(Integer studentId) {
        return null;
    }

    @Override
    public Boolean authenticateStudent(Student student) {
        return null;
    }

    @Override
    public Boolean isValidPayment(Payment payment) {
        return null;
    }

    @Override
    public Boolean isValidLecturer(Lecturer lecturer) {
        return null;
    }

    @Override
    public Boolean isValidLecturer(Integer lecturerId) {
        return null;
    }

    @Override
    public Boolean authenticateLecturer(Lecturer lecturer) {
        return null;
    }
}
