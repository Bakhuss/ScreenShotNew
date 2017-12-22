package ru.bakhuss.ScreenShotNew.model.media;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Media {

    protected BufferedImage media = null;
    protected String name = null;
    protected Long dateIn = null;
    protected int id = 0;

    protected Media(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDateIn() {
        return dateIn;
    }

    public void setDateIn(Long dateIn) {
        this.dateIn = dateIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
