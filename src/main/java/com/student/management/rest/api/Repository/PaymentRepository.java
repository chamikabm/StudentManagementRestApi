package com.student.management.rest.api.Repository;

import com.student.management.rest.api.Entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Integer> {
}
