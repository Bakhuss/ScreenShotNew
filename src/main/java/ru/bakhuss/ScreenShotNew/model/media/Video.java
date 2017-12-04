package ru.bakhuss.ScreenShotNew.model.media;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.util.Map;

public class Video extends Media {

    public Video(Person person) {
        this.person = person;
    }

    @Override
    public Map getMap() {
        return map;
    }

    /*
    public Video(Person person) {
        super(person);
//        this.type = MediaType.VIDEO;
    }
*/
}
