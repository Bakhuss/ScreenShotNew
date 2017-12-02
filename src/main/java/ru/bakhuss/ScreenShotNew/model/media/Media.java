package ru.bakhuss.ScreenShotNew.model.media;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.util.LinkedHashMap;

public class Media {
    private LinkedHashMap map = null;
    private Person person = null;

    public Media(Person person) {
        this.person = person;
        map = new LinkedHashMap();
    }

    public Person getPerson() {
        return person;
    }

    public int size() {
        return map.size();
    }
}
