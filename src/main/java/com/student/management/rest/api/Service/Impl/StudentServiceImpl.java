package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.StudentEntity;
import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Repository.StudentRepository;
import com.student.management.rest.api.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public String getWelcomeMessage() {
        LOGGER.info("Welcome!!!");
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
        StudentEntity studentEntity = convertStudentDtoToDao(student);
        this.studentRepository.save(studentEntity);
    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudentById(Integer id) {

    }

    @Override
    public List<Student> findAllStudents() {

        List<StudentEntity> studentEntities = new ArrayList<>();
        this.studentRepository.findAll().forEach(studentEntities::add);

        return  studentEntities.stream()
                .map(this::convertStudentDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findAllStudentsByDepartment(String department) {
        return null;
    }

    @Override
    public void deleteAllStudents() {
        return ;
    }

    @Override
    public void deleteAllStudentsByDepartment(String department) {

    }

    @Override
    public boolean isStudentExist(Student student) {
        return this.studentRepository.exists(student.getId());
    }

    private Student convertStudentDaoToDto(StudentEntity studentEntity) {
        Student student = new Student();
        student.setId(studentEntity.getId());
        student.setName(studentEntity.getName());
        student.setAge(studentEntity.getAge());
        student.setDepartment(studentEntity.getDepartment());

        return  student;
    }

    private StudentEntity convertStudentDtoToDao(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        studentEntity.setName(student.getName());
        studentEntity.setAge(student.getAge());
        studentEntity.setDepartment(student.getDepartment());

        return  studentEntity;
    }
}
