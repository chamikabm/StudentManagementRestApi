package com.student.management.rest.api.Repository;

import com.student.management.rest.api.Entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer> {
}
