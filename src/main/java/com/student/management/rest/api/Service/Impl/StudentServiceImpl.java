package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.StudentEntity;
import com.student.management.rest.api.Manager.StudentManager;
import com.student.management.rest.api.Manager.LecturerManager;
import com.student.management.rest.api.Manager.PaymentManager;
import com.student.management.rest.api.Model.Student;
import com.student.management.rest.api.Repository.LecturerRepository;
import com.student.management.rest.api.Repository.StudentRepository;
import com.student.management.rest.api.Service.StudentService;
import com.student.management.rest.api.Util.CustomErrorType;
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
    private final LecturerRepository lecturerRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, LecturerRepository lecturerRepository) {
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Student findById(Integer id) throws CustomErrorType {
        LOGGER.info("SMAPI - Student - Service- findById method invoked.");

        StudentEntity studentEntity = this.studentRepository.findOne(id);

        LOGGER.info("SMAPI - Student - Service- findById method processed.");
        if (studentEntity == null) {
            throw new CustomErrorType("Student with id " + id + " not found");
        } else {
            return convertStudentDaoToDto(studentEntity);
        }
    }

    @Override
    public Student findByName(String name) {
        return null;
    }

    @Override
    public Student saveStudent(Student student) throws CustomErrorType {
        LOGGER.info("SMAPI - Student - Service- saveStudent method invoked.");

        StudentManager studentManager = new StudentManager();

        LOGGER.info("SMAPI - Student - Service- saveStudent method processed.");

        if(studentManager.isValidStudent(student)) {
            StudentEntity studentEntity = convertStudentDtoToDao(student);
            return convertStudentDaoToDto(this.studentRepository.save(studentEntity));
        } else {
            throw new CustomErrorType("Invalid Student Object Found!");
        }
    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudentById(Integer id) {
        LOGGER.info("SMAPI - Student - Service- deleteStudentById method invoked.");

        this.studentRepository.delete(id);

        LOGGER.info("SMAPI - Student - Service- deleteStudentById method processed.");
    }

    @Override
    public List<Student> findAllStudents() {
        LOGGER.info("SMAPI - Student - Service- findAllStudents method invoked.");

        List<StudentEntity> studentEntities = new ArrayList<>();
        this.studentRepository.findAll().forEach(studentEntities::add);

        LOGGER.info("SMAPI - Student - Service- findAllStudents method processed.");

        return  studentEntities.stream()
                .map(this::convertStudentDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findAllStudentsByDepartment(String department) {
        LOGGER.info("SMAPI - Student - Service- findAllStudentsByDepartment method invoked.");
        LOGGER.info("SMAPI - Student - Service- findAllStudentsByDepartment method processed.");
        return null;
    }

    @Override
    public List<Student> findAllStudentsByLecturer(Integer id) {

        LOGGER.info("SMAPI - Student - Service- findAllStudentsByLecturer method invoked.");

        LecturerManager lecturerManager = new LecturerManager();
        List<StudentEntity> studentEntities = new ArrayList<>();

        if (lecturerManager.isValidLecturerId(id) && lecturerRepository.exists(id)) {

            this.studentRepository.findAll().forEach(studentEntities::add);
        }

        LOGGER.info("SMAPI - Student - Service- findAllStudentsByLecturer method processed.");

        return  studentEntities.stream()
                .map(this::convertStudentDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findAllStudentsWhoHasMadePayments() {
        LOGGER.info("SMAPI - Student - Service- findAllStudentsWhoHasMadePayments method invoked.");
        PaymentManager paymentManager = new PaymentManager();
        List<Student> students = findAllStudents();
        List<Student> paidStudents = new ArrayList<>();

        for (Student student : students) {
            if (paymentManager.isValidPaymentId(student.getId())) {
                paidStudents.add(student);
            }
        }

        LOGGER.info("SMAPI - Student - Service- findAllStudentsWhoHasMadePayments method processed.");
        return paidStudents;
    }

    @Override
    public void deleteAllStudents() {
        LOGGER.info("SMAPI - Student - Service- deleteAllStudents method invoked.");

        LOGGER.info("SMAPI - Student - Service- deleteAllStudents method processed.");
        this.studentRepository.deleteAll();
    }

    @Override
    public void deleteAllStudentsByDepartment(String department) {

    }

    @Override
    public boolean isStudentExist(Student student) {
        LOGGER.info("SMAPI - Student - Service- isStudentExist method invoked.");

        LOGGER.info("SMAPI - Student - Service- isStudentExist method processed.");
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
