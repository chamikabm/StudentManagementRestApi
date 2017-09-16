package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Student;

public interface AuthenticationService {

    Boolean isValidStudent(Integer studentId);

    Boolean authenticateStudent(Student student);
}
