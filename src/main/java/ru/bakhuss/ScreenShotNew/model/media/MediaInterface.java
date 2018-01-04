package ru.bakhuss.ScreenShotNew.model.media;

import java.util.List;

public abstract class MediaInterface<T extends Media> {
    protected Long dateIn = null;

    protected List<T> mediaGroup = null;


    public Long getDateIn() {
        return dateIn;
    }

    public void setDateIn(Long dateIn) {
        this.dateIn = dateIn;
    }
}
