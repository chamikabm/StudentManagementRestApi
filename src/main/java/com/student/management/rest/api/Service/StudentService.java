package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Util.DepartmentType;

import java.util.List;

public interface StudentService {

    String getWelcomeMessage();

    Student findById(Integer id);

    Student findByName(String name);

    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(Integer id);

    List<Student> findAllStudents();

    List<Student> findAllStudentsByDepartment(DepartmentType department);

    void deleteAllStudents();

    void deleteAllStudentsByDepartment(DepartmentType department);

    boolean isStudentExist(Student student);
}
