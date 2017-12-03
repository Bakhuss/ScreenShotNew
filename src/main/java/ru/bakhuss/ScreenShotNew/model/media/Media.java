package ru.bakhuss.ScreenShotNew.model.media;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

public class Media<T extends Media> {
    private LinkedHashMap<Long, BufferedImage> map = null;
    private Person person = null;
//    protected MediaType type = null;

    public Media(Person person) {
        this.person = person;
        map = new LinkedHashMap<Long, BufferedImage>();
    }

    public LinkedHashMap<Long, BufferedImage> getMap() {
        return map;
    }

    public Person getPerson() {
        return person;
    }

//    public MediaType getType() {
//        return type;
//    }

    enum  MediaType {
        PHOTO, AUDIO, VIDEO;
    }
}
