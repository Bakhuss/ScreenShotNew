package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.media.MediaGroup;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MediaRepository extends Repository<Media> {

    void set(Media media) throws SQLException;

    void set(MediaGroup mediaGroup) throws SQLException;

    void set(Media... media) throws SQLException;


    Media get() throws SQLException;

    ArrayList<Media> getAll() throws SQLException;

    void update(Media media) throws SQLException;

    void remove(Media media) throws SQLException;
}
