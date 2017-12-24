package com.student.management.rest.api.Service;

import com.student.management.rest.api.Model.Lecturer;
import com.student.management.rest.api.Util.CustomErrorType;

import java.util.List;

public interface LecturerService {
    List<Lecturer> findAllLecturers();

    Lecturer findById(Integer id) throws CustomErrorType;

    boolean isLecturerExist(Lecturer lecturer);

    void addNewLecturer(Lecturer lecturer);

    void updateLecturer(Lecturer currentLecturer);

    void deleteLecturerById(Integer id);

    void deleteAllLecturers();
}
