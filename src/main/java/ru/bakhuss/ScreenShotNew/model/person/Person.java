package ru.bakhuss.ScreenShotNew.model.person;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.bakhuss.ScreenShotNew.model.CommonFields;
import ru.bakhuss.ScreenShotNew.model.DBTables;
import ru.bakhuss.ScreenShotNew.model.media.MediaAbstract;

import java.util.ArrayList;
import java.util.List;

public class Person extends CommonFields {

    private StringProperty surname;
    private StringProperty firstName;
    private StringProperty patronymic;
    private List<? super MediaAbstract> mediaList = null;
    private PersonalData persData = null;

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

    public Long getDateIn() {
        return dateIn;
    }

    public void setDateIn(Long dateIn) {
        this.dateIn = dateIn;
    }

    public List<? super MediaAbstract> getPersonMedia() {
        if (mediaList == null) mediaList = new ArrayList<>();
        return mediaList;
    }

    public PersonalData getPersData() {
        return persData;
    }

    public void setPersData(PersonalData persData) {
        this.persData = persData;
    }

    public String getFullName() {
        return firstName.get().concat(" ").concat(patronymic.get()).concat(" ").concat(surname.get());
    }

}
