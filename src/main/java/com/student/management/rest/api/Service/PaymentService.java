package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAllPayments();

    Payment findById(Integer id);

    boolean isPaymentExist(Payment payment);

    void addNewPayment(Payment payment);

    void updatePayment(Payment currentPayment);

    void deletePaymentById(Integer id);

    void deleteAllPayments();
}
