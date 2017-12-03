package ru.bakhuss.ScreenShotNew.model.person;

public class PersonalData {
    private Person person;
    private String birthDay;
    private String berthPlace;
    private String deathDay;
    private String deathPlace;
    private String height;
    private String eyeColor;
    private String hairColor;


    public PersonalData(Person person) {
        this.person = person;
    }


    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBerthPlace() {
        return berthPlace;
    }

    public void setBerthPlace(String berthPlace) {
        this.berthPlace = berthPlace;
    }

    public String getDeathDay() {
        return deathDay;
    }

    public void setDeathDay(String deathDay) {
        this.deathDay = deathDay;
    }

    public String getDeathPlace() {
        return deathPlace;
    }

    public void setDeathPlace(String deathPlace) {
        this.deathPlace = deathPlace;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }
}
