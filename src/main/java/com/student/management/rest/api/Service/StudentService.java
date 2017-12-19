package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Student;

import java.util.List;

public interface StudentService {

    String getWelcomeMessage();

    Student findById(Integer id);

    Student findByName(String name);

    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(Integer id);

    List<Student> findAllStudents();

    List<Student> findAllStudentsByDepartment(String department);

    void deleteAllStudents();

    void deleteAllStudentsByDepartment(String department);

    boolean isStudentExist(Student student);
}
