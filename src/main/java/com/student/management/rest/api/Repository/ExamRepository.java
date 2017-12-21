package com.student.management.rest.api.Repository;

import com.student.management.rest.api.Entity.ExamEntity;
import org.springframework.data.repository.CrudRepository;

public interface ExamRepository extends CrudRepository<ExamEntity, Integer> {
}
