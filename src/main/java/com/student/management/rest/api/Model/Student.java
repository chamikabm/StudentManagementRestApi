package com.student.management.rest.api.Model;

import com.student.management.rest.api.Util.DepartmentType;

public class Student {

    private String name;
    private int age;
    private DepartmentType department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public DepartmentType getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentType department) {
        this.department = department;
    }
}

