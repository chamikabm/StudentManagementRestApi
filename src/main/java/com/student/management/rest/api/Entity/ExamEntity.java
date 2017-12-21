package com.student.management.rest.api.Entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="exam")
public class ExamEntity implements Serializable {

    private static final long serialVersionUID = 5740098458478706711L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer departmentId;
    private Integer courseId;
    private Date date;

    public ExamEntity() { }

    public ExamEntity(String name, Integer departmentId, Integer courseId, Date date) {
        this.name = name;
        this.departmentId = departmentId;
        this.courseId = courseId;
        this.date = date;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
