package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Lecturer;
import com.student.management.rest.api.Model.Payment;
import com.student.management.rest.api.Model.Student;

public interface AuthenticationService {

    Boolean isValidStudent (Student student);

    Boolean isValidStudent(Integer studentId);

    Boolean authenticateStudent(Student student);

    Boolean isValidPayment(Payment payment);

    Boolean isValidLecturer(Lecturer lecturer);

    Boolean isValidLecturer(Integer lecturerId);

    Boolean authenticateLecturer(Lecturer lecturer);
}
