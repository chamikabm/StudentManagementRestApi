package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.StudentEntity;
import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Repository.StudentRepository;
import com.student.management.rest.api.Service.StudentService;
import org.modelmapper.ModelMapper;
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
        this.studentRepository.delete(id);
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
        this.studentRepository.deleteAll();
    }

    @Override
    public void deleteAllStudentsByDepartment(String department) {

    }

    @Override
    public boolean isStudentExist(Student student) {
        return this.studentRepository.exists(student.getId());
    }

    private Student convertStudentDaoToDto(StudentEntity studentEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(studentEntity, Student.class);
    }

    private StudentEntity convertStudentDtoToDao(Student student) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(student, StudentEntity.class);
    }
}
