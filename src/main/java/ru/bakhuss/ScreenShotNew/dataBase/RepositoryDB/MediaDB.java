package ru.bakhuss.ScreenShotNew.dataBase.RepositoryDB;

import ru.bakhuss.ScreenShotNew.model.media.Media;

import java.sql.SQLException;
import java.util.Collection;


public interface MediaDB extends RepositoryDB<Media> {
    @Override
    void set(Media... m) throws SQLException;

    @Override
    Collection<Media> get() throws SQLException;

    @Override
    void update(Media... m) throws SQLException;

    @Override
    void delete(Media... m) throws SQLException;
}
