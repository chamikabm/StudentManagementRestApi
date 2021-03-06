package com.student.management.rest.api.Manager;

import com.student.management.rest.api.Model.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationManager.class);

    public Boolean isValidRegistration(Registration registration) {

        LOGGER.info("SMAPI - Registration - Manager- isValidRegistration method invoked.");

        LOGGER.info("SMAPI - Registration - Manager- isValidRegistration method processed.");

        if (registration.getStudentId() == null || registration.getIsRegistered() == null) {

            return Boolean.FALSE;
        } else {

            return Boolean.TRUE;
        }
    }
}
