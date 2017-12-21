package com.student.management.rest.api.Entity;

import com.student.management.rest.api.Enum.PaymentStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="payment")
public class PaymentEntity implements Serializable {

    private static final long serialVersionUID = 4780526925226890406L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer courseId;
    private Integer studentId;
    private Double amount;
    private Integer semester;
    private Date paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public PaymentEntity() { }

    public PaymentEntity(Integer courseId, Integer studentId, Double amount, Integer semester, Date paymentDate,
                         PaymentStatus status) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.amount = amount;
        this.semester = semester;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
