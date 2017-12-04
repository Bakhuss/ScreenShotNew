package ru.bakhuss.ScreenShotNew.model.media;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.util.Map;

public class Audio extends Media {

    public Audio(Person person) {
        this.person = person;
    }

    @Override
    public Map getMap() {
        return map;
    }
}
