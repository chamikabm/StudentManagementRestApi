package com.student.management.rest.api.Util;

public enum DepartmentType {
    Electronic("Electronic"),
    Civil("Civil"),
    Mechanical("Mechanical"),
    Chemical("Chemical"),
    ComputerScience("ComputerScience");

    private String displayName;

    DepartmentType(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }

    // Optionally and/or additionally, toString.
    @Override public String toString() { return displayName; }
}
