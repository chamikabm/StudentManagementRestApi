package com.student.management.rest.api.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="department")
public class DepartmentEntity implements Serializable {

    private static final long serialVersionUID = -7262991667111930417L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String departmentHeadId;

    public DepartmentEntity() {}

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

    public String getDepartmentHeadId() {
        return departmentHeadId;
    }

    public void setDepartmentHeadId(String departmentHeadId) {
        this.departmentHeadId = departmentHeadId;
    }
}
