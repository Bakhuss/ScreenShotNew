package ru.bakhuss.ScreenShotNew.model.media;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Media<K, V> {
    protected Person person = null;
    protected Map<K, V> map = null;
    protected String tempName = null;
    protected int groupNameId = 0;

    public abstract Map<K, V> getMap();

    public Person getPerson() {
        return person;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public void setGroupNameId(int groupNameId) {
        this.groupNameId = groupNameId;
    }

    public int getGroupNameId() {
        return groupNameId;
    }

    enum  MediaType {
        PHOTO, AUDIO, VIDEO;
    }
}
