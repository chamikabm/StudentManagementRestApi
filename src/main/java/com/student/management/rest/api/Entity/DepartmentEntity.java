package com.student.management.rest.api.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="DEPARTMENT")
public class DepartmentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String departmentHead;

    public DepartmentEntity() {}

    public DepartmentEntity(String name, String departmentHead) {
        this.name = name;
        this.departmentHead = departmentHead;
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

    public String getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }
}
