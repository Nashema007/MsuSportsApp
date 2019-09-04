package com.example.msusportsapp.modals;

public class SportStudentsModal {

    private String regnumber;
    private String firstname;
    private String surname;
    private String level;

    public SportStudentsModal(String regnumber, String firstname, String surname, String level) {
        this.regnumber = regnumber;
        this.firstname = firstname;
        this.surname = surname;
        this.level = level;
    }

    public SportStudentsModal(String regnumber) {
        this.regnumber = regnumber;
    }

    public String getRegnumber() {
        return regnumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getLevel() {
        return level;
    }
}
