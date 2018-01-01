package ru.bakhuss.ScreenShotNew.model.media;

import java.util.List;

public abstract class MediaInterface<T extends Media> {
    protected List<T> mediaGroup = null;
}
