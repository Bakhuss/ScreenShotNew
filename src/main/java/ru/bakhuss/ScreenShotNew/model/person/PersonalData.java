package ru.bakhuss.ScreenShotNew.model.person;

public abstract class PersonalData {
    private String birthday;


    public PersonalData() {

    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
