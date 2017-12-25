package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Payment;

public class PaymentManager {

    public Boolean isValidPayment(Payment payment) {

        if (payment.getAmount() == null || payment.getCourseId() == null
                || payment.getPaymentStatus() == null || payment.getSemester() == null
                || payment.getStudentId() == null || !isValidAmount(payment.getAmount())) {

            return Boolean.FALSE;
        } else {

            return Boolean.TRUE;
        }
    }

    public Boolean isValidAmount(Double amount) {
        try {
            Double.parseDouble(Double.toString(amount));

            return Boolean.TRUE;
        } catch(Exception e) {
            return Boolean.FALSE;
        }
    }
}
