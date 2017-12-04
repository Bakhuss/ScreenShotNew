package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.media.Media;

public interface MediaRepository extends Repository<Media> {

    void set(Media media);

    Media get();

    void update(Media media);

    void remove(Media media);
}
