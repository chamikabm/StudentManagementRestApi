package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentManager.class);

    public Boolean isValidPayment(Payment payment) {

        LOGGER.info("Payment - Manager- isValidPayment method invoked.");

        LOGGER.info("Payment - Manager- isValidPayment method processed.");

        if (payment.getAmount() == null || payment.getCourseId() == null
                || payment.getPaymentStatus() == null || payment.getSemester() == null
                || payment.getStudentId() == null || !isValidAmount(payment.getAmount())) {

            return Boolean.FALSE;
        } else {

            return Boolean.TRUE;
        }
    }

    public Boolean isValidAmount(Double amount) {

        LOGGER.info("Payment - Manager- isValidAmount method invoked.");

        LOGGER.info("Payment - Manager- isValidAmount method processed.");

        try {
            Double.parseDouble(Double.toString(amount));

            return Boolean.TRUE;
        } catch(Exception e) {
            return Boolean.FALSE;
        }
    }
}
