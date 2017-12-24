package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.DepartmentEntity;
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
        DepartmentEntity departmentEntity = this.departmentRepository.findOne(id);
        if (departmentEntity == null) {
            throw new CustomErrorType("Department with id " + id + " not found");
        } else {
            return convertDaoToDto(departmentEntity);
        }
    }

    @Override
    public Department findByName(String departmentName) {
        return null;
    }

    @Override
    public void addNewDepartment(Department department) {
        DepartmentEntity departmentEntity = convertDtoToDao(department);
        this.departmentRepository.save(departmentEntity);
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartmentById(Integer id) {
        this.departmentRepository.delete(id);
    }

    @Override
    public List<Department> findAllDepartments() {
        List<DepartmentEntity> departmentEntities = new ArrayList<>();
        this.departmentRepository.findAll().forEach(departmentEntities::add);

        return  departmentEntities.stream()
                .map(this::convertDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isDepartmentExist(Department department) {
        return this.departmentRepository.exists(department.getId());
    }

    @Override
    public void deleteAllDepartments() {
        this.departmentRepository.deleteAll();
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
