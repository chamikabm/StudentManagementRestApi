package com.student.management.rest.api.Repository;

import com.student.management.rest.api.Entity.LecturerEntity;
import org.springframework.data.repository.CrudRepository;

public interface LecturerRepository extends CrudRepository<LecturerEntity, Integer> {
}
