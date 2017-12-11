package ru.bakhuss.ScreenShotNew.model.person;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

    private StringProperty surname;
    private StringProperty firstName;
    private StringProperty patronymic;
    private int personIdInDB = 0;

    // Constructors
    public Person() {
        this.surname = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.patronymic = new SimpleStringProperty();
    }

    public Person(String surname, String firstName, String patronymic) {
        this.surname = new SimpleStringProperty(surname);
        this.firstName = new SimpleStringProperty(firstName);
        this.patronymic = new SimpleStringProperty(patronymic);
    }



    //Getters and Setters
    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }

    public StringProperty patronymicProperty() {
        return patronymic;
    }

    public int getPersonIdInDB() { return personIdInDB; }

    public void setPersonIdInDB(int personIdInDB) {
        this.personIdInDB = personIdInDB;
    }
}
