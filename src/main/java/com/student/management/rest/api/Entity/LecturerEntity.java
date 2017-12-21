package com.student.management.rest.api.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="lecturer")
public class LecturerEntity implements Serializable {

    private static final long serialVersionUID = -8857279483644492778L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String contactNo;
    private String address;
    private String email;
    private Integer departmentId;

    public LecturerEntity() { }

    public LecturerEntity(String name, Integer age, String contactNo, String address,
                          String email, Integer departmentId) {
        this.name = name;
        this.age = age;
        this.contactNo = contactNo;
        this.address = address;
        this.email = email;
        this.departmentId = departmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
