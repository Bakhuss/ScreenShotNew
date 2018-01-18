package ru.bakhuss.ScreenShotNew.save;

import ru.bakhuss.ScreenShotNew.save.impl.Place;

public abstract class SavePlace {

    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public abstract Place save();

}
