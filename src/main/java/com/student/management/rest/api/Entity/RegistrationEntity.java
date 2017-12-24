package com.student.management.rest.api.Entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student_register")
public class RegistrationEntity implements Serializable {

    private static final long serialVersionUID = -5743511979559979293L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer studentId;
    private Integer isRegistered;
    private Date registeredDate;

    public RegistrationEntity() { }

    public RegistrationEntity(Integer id, Integer studentId, Integer isRegistered,
                              Date registeredDate) {
        this.id = id;
        this.studentId = studentId;
        this.isRegistered = isRegistered;
        this.registeredDate = registeredDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Integer isRegistered) {
        this.isRegistered = isRegistered;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
}
