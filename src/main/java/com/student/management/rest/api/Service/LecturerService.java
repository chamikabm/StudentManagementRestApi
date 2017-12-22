package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Lecturer;

import java.util.List;

public interface LecturerService {
    List<Lecturer> findAllLecturers();

    Lecturer findById(Integer id);

    boolean isLecturerExist(Lecturer lecturer);

    void addNewLecturer(Lecturer lecturer);

    void updateLecturer(Lecturer currentLecturer);

    void deleteLecturerById(Integer id);

    void deleteAllLecturers();
}
