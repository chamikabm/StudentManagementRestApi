package com.student.management.rest.api.Enum;

public enum PaymentStatus {
    PENDING("PENDING"),
    PAID("PAID"),
    FREE("FREE");

    private final String paymentStatus;

    private PaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}
