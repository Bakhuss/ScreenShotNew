package ru.bakhuss.ScreenShotNew.model.media;

import java.util.ArrayList;
import java.util.List;

public class MediaGroup<T extends Media> extends MediaAbstract {

    private boolean autoscreen = false;
    private int elementsCount = 0;
    private List<T> mediaGroup = null;


    public MediaGroup(){}

    public MediaGroup(boolean autoscreen) {
        this.autoscreen = autoscreen;
    }


    public List<T> getMediaList() {
        if (mediaGroup == null) mediaGroup = new ArrayList<>();
        return mediaGroup;
    }

    public boolean isAutoscreen() {
        return autoscreen;
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public void setElementsCount(int elementsCount) {
        this.elementsCount = elementsCount;
    }

    public void setAutoscreen(boolean autoscreen) {
        this.autoscreen = autoscreen;
    }
}
