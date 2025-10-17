package com.exam.model;

import java.util.Date;

public class User{
    private String lastname;
    private String firstname;
    private String middleName;
    private String birthdate;

    public User() {

    }

    public User(String lastname, String firstname, String middleName, String birthdate) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.middleName = middleName;
        this.birthdate = birthdate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
