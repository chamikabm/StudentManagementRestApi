package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public String getWelcomeMessage() {
        return "Welcome!!";
    }
}
