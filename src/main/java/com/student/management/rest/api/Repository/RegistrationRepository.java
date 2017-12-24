package com.student.management.rest.api.Repository;

import com.student.management.rest.api.Entity.RegistrationEntity;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<RegistrationEntity, Integer> {
}
