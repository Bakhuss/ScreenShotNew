package ru.bakhuss.ScreenShotNew.model.media;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.awt.image.BufferedImage;
import java.util.*;

public class Photo extends Media<Long, BufferedImage> {
//    private Person person = null;
//    private LinkedHashMap<Long, BufferedImage> map = null;

    public Photo(Person person) {
        this.person = person;
        this.map = new LinkedHashMap<>();
//        this.map = new HashMap<>();
    }

    public Photo(String tempName, int groupNameId) {
        this.tempName = tempName;
        this.groupNameId = groupNameId;
        this.map = new LinkedHashMap<>();
//        this.map = new HashMap<>();
    }

    @Override
    public Map<Long, BufferedImage> getMap() {
        return map;
    }









/*

    public Photo(Person person) {
        super(person);
//        this.type = MediaType.PHOTO;
    }


    /*


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
*/

}
