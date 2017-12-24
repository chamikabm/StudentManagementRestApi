package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Registration;
import com.student.management.rest.api.Model.Student;

import java.util.List;

public interface RegistrationService {

    Registration registerNewStudent(Student student);

    Registration getStudentRegistration(Student student);

    Boolean removeStudentRegistration(Integer studentId);

    Boolean isRegisteredStudent(Integer studentId);

    List<Registration> findAllRegistrations();
}
