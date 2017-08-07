package com.student.management.rest.api.Repository;

import com.student.management.rest.api.Entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository  extends CrudRepository<StudentEntity, Integer> {
}
