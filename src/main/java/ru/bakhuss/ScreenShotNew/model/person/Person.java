package ru.bakhuss.ScreenShotNew.model.person;

public class Person {

    private String surname;
    private String firstName;
    private String patronymic;
    private String birthday;
    private String tempName;

    public Person() {
        this.surname = null;
        this.firstName = null;
        this.patronymic = null;
        this.birthday = null;
        this.tempName = null;
    }

    public Person(String surname, String firstName, String patronymic, String birthday) {
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.tempName = null;
    }

    public Person(String tempName) {
        this.surname = null;
        this.firstName = null;
        this.patronymic = null;
        this.birthday = null;
        this.tempName = tempName;
    }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
