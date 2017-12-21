package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAllPayments();
}
