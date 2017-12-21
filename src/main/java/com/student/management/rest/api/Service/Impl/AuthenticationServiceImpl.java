package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public Boolean isValidStudent(Integer studentId) {
        return null;
    }

    @Override
    public Boolean authenticateStudent(Student student) {
        return null;
    }
}
