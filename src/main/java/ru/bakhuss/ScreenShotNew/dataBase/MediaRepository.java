package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.media.Media;

public interface MediaRepository {

    void setMedia();

    Media getMedia();

    void updateMedia();

    void removeMedia();
}
