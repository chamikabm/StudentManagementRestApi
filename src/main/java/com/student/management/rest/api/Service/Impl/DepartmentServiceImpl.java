package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.DepartmentEntity;
import com.student.management.rest.api.Manager.DepartmentManager;
import com.student.management.rest.api.Manager.RegistrationManager;
import com.student.management.rest.api.Model.Department;
import com.student.management.rest.api.Repository.DepartmentRepository;
import com.student.management.rest.api.Service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department findById(Integer id) throws CustomErrorType {
        LOGGER.info("SMAPI - Department - Service- findById method invoked.");

        DepartmentEntity departmentEntity = this.departmentRepository.findOne(id);
        if (departmentEntity == null) {
            LOGGER.info("SMAPI - Department - Service- findById method processed.");
            throw new CustomErrorType("Department with id " + id + " not found");
        } else {
            LOGGER.info("SMAPI - Department - Service- findById method processed.");
            return convertDaoToDto(departmentEntity);
        }
    }

    @Override
    public Department findByName(String departmentName) {
        return null;
    }

    @Override
    public void addNewDepartment(Department department) throws CustomErrorType {
        LOGGER.info("SMAPI - Department - Service- addNewDepartment method invoked.");

        DepartmentManager departmentManager = new DepartmentManager();

        if (departmentManager.isValidDepartment(department)) {
            DepartmentEntity departmentEntity = convertDtoToDao(department);
            this.departmentRepository.save(departmentEntity);
        } else {
            LOGGER.info("SMAPI - Department - Service- addNewDepartment method processed.");
            throw new CustomErrorType("Invalid Department Object Found!");
        }

        LOGGER.info("SMAPI - Department - Service- addNewDepartment method processed.");
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartmentById(Integer id) {
        LOGGER.info("SMAPI - Department - Service- deleteDepartmentById method invoked.");

        this.departmentRepository.delete(id);

        LOGGER.info("SMAPI - Department - Service- deleteDepartmentById method processed.");
    }

    @Override
    public List<Department> findAllDepartments() {
        LOGGER.info("SMAPI - Department - Service- findAllDepartments method invoked.");

        List<DepartmentEntity> departmentEntities = new ArrayList<>();
        this.departmentRepository.findAll().forEach(departmentEntities::add);

        LOGGER.info("SMAPI - Department - Service- findAllDepartments method processed.");

        return  departmentEntities.stream()
                .map(this::convertDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isDepartmentExist(Department department) {
        LOGGER.info("SMAPI - Department - Service- isDepartmentExist method invoked.");

        LOGGER.info("SMAPI - Department - Service- isDepartmentExist method processed.");
        return this.departmentRepository.exists(department.getId());
    }

    @Override
    public void deleteAllDepartments() {
        LOGGER.info("SMAPI - Department - Service- deleteAllDepartments method invoked.");

        this.departmentRepository.deleteAll();

        LOGGER.info("SMAPI - Department - Service- deleteAllDepartments method processed.");
    }

    private Department convertDaoToDto(DepartmentEntity departmentEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(departmentEntity, Department.class);
    }

    private DepartmentEntity convertDtoToDao(Department department) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(department, DepartmentEntity.class);
    }
}
