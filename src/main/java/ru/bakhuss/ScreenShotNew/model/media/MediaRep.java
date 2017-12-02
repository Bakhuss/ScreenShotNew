package ru.bakhuss.ScreenShotNew.model.media;

import ru.bakhuss.ScreenShotNew.model.person.Repository;

public interface MediaRep extends Repository<Photo> {
    void set(Photo photo);

    Photo get();

    void update(Photo photo);

    void remove(Photo photo);
}
