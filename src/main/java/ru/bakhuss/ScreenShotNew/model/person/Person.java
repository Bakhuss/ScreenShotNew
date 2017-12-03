package ru.bakhuss.ScreenShotNew.model.person;

public class Person {

    private String surname;
    private String firstName;
    private String patronymic;
    private String tempName;

    // Constructors
    public Person() {
        this.surname = null;
        this.firstName = null;
        this.patronymic = null;
        this.tempName = null;
    }

    public Person(String surname, String firstName, String patronymic) {
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.tempName = null;
    }

    public Person(String tempName) {
        this.surname = null;
        this.firstName = null;
        this.patronymic = null;
        this.tempName = tempName;
    }


    //Getters and Setters
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }
}
