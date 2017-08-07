package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.StudentEntity;
import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Repository.StudentRepository;
import com.student.management.rest.api.Service.StudentService;
import com.student.management.rest.api.Util.DepartmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome!!";
    }

    @Override
    public Student findById(Integer id) {
        return convertStudentDaoToDto(studentRepository.findOne(id));
    }

    @Override
    public Student findByName(String name) {
        return null;
    }

    @Override
    public void saveStudent(Student student) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudentById(Integer id) {

    }

    @Override
    public List<Student> findAllStudents() {
        return null;
    }

    @Override
    public List<Student> findAllStudentsByDepartment(DepartmentType department) {
        return null;
    }

    @Override
    public void deleteAllStudents() {

    }

    @Override
    public void deleteAllStudentsByDepartment(DepartmentType department) {

    }

    @Override
    public boolean isStudentExist(Student student) {
        return false;
    }

    private Student convertStudentDaoToDto(StudentEntity studentEntity) {
        Student student = new Student();
        student.setId(studentEntity.getId());
        student.setName(studentEntity.getName());
        student.setAge(studentEntity.getAge());
        student.setDepartment(DepartmentType.valueOf(studentEntity.getDepartment()));

        return  student;
    }
}
