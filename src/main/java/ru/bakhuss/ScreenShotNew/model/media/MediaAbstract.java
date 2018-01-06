package ru.bakhuss.ScreenShotNew.model.media;


import ru.bakhuss.ScreenShotNew.model.CommonFields;

public class MediaAbstract extends CommonFields {
    protected String name = null;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
