package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Student;

public interface RegistrationService {

    Boolean registerNewStudent(Student student);

    Boolean removeStudentRegistration(Student student);

    Boolean isRegisterStudent(Integer studentId);
}
