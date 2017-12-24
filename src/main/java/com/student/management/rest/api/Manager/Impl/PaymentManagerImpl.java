package com.student.management.rest.api.Manager.Impl;

import com.student.management.rest.api.Manager.PaymentManager;
import com.student.management.rest.api.Model.Payment;

public class PaymentManagerImpl implements PaymentManager {

    @Override
    public Boolean isValidPayment(Payment payment) {

        if (payment.getAmount() == null || payment.getCourseId() == null
                || payment.getPaymentStatus() == null || payment.getSemester() == null
                || payment.getStudentId() == null || !isValidAmount(payment.getAmount())) {

            return Boolean.FALSE;
        } else {

            return Boolean.TRUE;
        }
    }

    @Override
    public Boolean isValidAmount(Double amount) {
        try {
            Double.parseDouble(Double.toString(amount));

            return Boolean.TRUE;
        } catch(Exception e) {
            return Boolean.FALSE;
        }
    }
}
