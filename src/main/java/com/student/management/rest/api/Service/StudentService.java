package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Util.CustomErrorType;

import java.util.List;

public interface StudentService {

    Student findById(Integer id) throws CustomErrorType;

    Student findByName(String name);

    Student saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(Integer id);

    List<Student> findAllStudents();

    List<Student> findAllStudentsByDepartment(String department);

    void deleteAllStudents();

    void deleteAllStudentsByDepartment(String department);

    boolean isStudentExist(Student student);
}
