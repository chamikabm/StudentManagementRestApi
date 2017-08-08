package com.student.management.rest.api.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="STUDENT")
public class StudentEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String department;

    public StudentEntity() {}

    public StudentEntity(String name, Integer age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
