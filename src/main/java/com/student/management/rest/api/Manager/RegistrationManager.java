package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Registration;

public class RegistrationManager {

    public Boolean isValidRegistration(Registration registration) {

        if (registration.getStudentId() == null || registration.getIsRegistered() == null) {

            return Boolean.FALSE;
        } else {

            return Boolean.TRUE;
        }
    }
}
