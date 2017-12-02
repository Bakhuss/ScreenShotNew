package ru.bakhuss.ScreenShotNew.model.media;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.awt.image.BufferedImage;
import java.util.*;

public class Photo {

    private LinkedHashMap map = null;
    private Person person = null;

    public Photo(Person person) {
        this.person = person;
        map = new LinkedHashMap();
    }

    public void addPhoto(Long time, BufferedImage bi) {
        map.put(time, bi);
    }

    public void addPhoto(BufferedImage bi) {
        map.put(null, bi);
    }

    public Person getPerson() {
        return this.person;
    }

    public int size() {
        return map.size();
    }

}
