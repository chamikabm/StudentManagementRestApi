package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Payment;

public interface PaymentManager {

    Boolean isValidPayment(Payment payment);

    Boolean isValidAmount(Double amount);
}
