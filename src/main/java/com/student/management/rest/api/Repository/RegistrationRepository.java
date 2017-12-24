package com.student.management.rest.api.Repository;

import com.student.management.rest.api.Entity.RegistrationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RegistrationRepository extends CrudRepository<RegistrationEntity, Integer> {

    @Query("SELECT RE FROM RegistrationEntity RE WHERE RE.studentId=:studentId")
    RegistrationEntity findByStudentId(@Param("studentId") Integer studentId);

    @Query("DELETE FROM RegistrationEntity RE WHERE RE.studentId=:studentId")
    RegistrationEntity deleteByStudentId(@Param("studentId") Integer studentId);
}
