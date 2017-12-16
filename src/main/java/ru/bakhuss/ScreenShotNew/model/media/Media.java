package ru.bakhuss.ScreenShotNew.model.media;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Media<T extends Media> {

    // one media
    protected BufferedImage media = null;
    protected String name = null;

    // group media
    private List<T> mediaGroup = null;
    private String groupName = null;
    private int groupNameId = 0;

    // mutual
    protected Long dateIn = null;
    protected Person person = null;


    public Media() {
        this.mediaGroup = new ArrayList<>();
    }


    protected BufferedImage getMedia() {
        return media;
    }

    protected void setMedia(BufferedImage media) {
        this.media = media;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public Long getDateIn() {
        return dateIn;
    }

    public void setDateIn(Long dateIn) {
        this.dateIn = dateIn;
    }

    public List<T> getMediaGroup() {
        return mediaGroup;
    }

    public int getGroupNameId() {
        return groupNameId;
    }

    public void setGroupNameId(int groupNameId) {
        this.groupNameId = groupNameId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
