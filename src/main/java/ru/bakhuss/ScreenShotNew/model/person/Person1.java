package ru.bakhuss.ScreenShotNew.model.person;

public class Person1 extends PersonalData {
    private String surname;
    private String firstName;
    private String patronymic;
    private String tempName;

    public Person1(String surname, String firstName, String patronymic, String tempName) {
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.tempName = tempName;
    }

    public Person1() {
        this.surname = null;
        this.firstName = null;
        this.patronymic = null;
        this.tempName = null;
    }
}
