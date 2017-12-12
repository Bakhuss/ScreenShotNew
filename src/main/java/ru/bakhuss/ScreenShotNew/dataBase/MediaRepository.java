package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.media.Media;

import java.util.ArrayList;

public interface MediaRepository extends Repository<Media> {

    void set(Media media);

    Media get();

    ArrayList<Media> getAll();

    void update(Media media);

    void remove(Media media);
}
