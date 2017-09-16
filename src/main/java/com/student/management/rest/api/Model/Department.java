package com.student.management.rest.api.Model;

import com.student.management.rest.api.Util.DepartmentType;

public class Department {
    private int id;
    private String name;
    private DepartmentType department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentType getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentType department) {
        this.department = department;
    }
}
