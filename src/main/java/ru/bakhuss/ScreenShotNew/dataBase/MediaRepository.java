package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.media.Media;

public interface MediaRepository extends Repository<Media<? extends Media>> {

    void set(Media<? extends Media> media);

    Media<? extends Media> get();

    void update(Media<? extends Media> media);

    void remove(Media<? extends Media> media);
}
