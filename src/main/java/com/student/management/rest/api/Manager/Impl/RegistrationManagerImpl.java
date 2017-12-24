package com.student.management.rest.api.Manager.Impl;

import com.student.management.rest.api.Manager.RegistrationManager;
import com.student.management.rest.api.Model.Registration;

public class RegistrationManagerImpl implements RegistrationManager {

    @Override
    public Boolean isValidRegistration(Registration registration) {

        if (registration.getStudentId() == null || registration.getIsRegistered() == null) {

            return Boolean.FALSE;
        } else {

            return Boolean.TRUE;
        }
    }
}
