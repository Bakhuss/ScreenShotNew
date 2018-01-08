package ru.bakhuss.ScreenShotNew.save.impl.repository;

import ru.bakhuss.ScreenShotNew.model.media.Media;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface MediaRep extends Repository<Media> {
    @Override
    void set(Media... m) throws SQLException, IOException;

    @Override
    Collection<Media> get() throws SQLException, IOException;

    @Override
    void update(Media... m) throws SQLException, IOException;

    @Override
    void delete(Media... m) throws SQLException, IOException;
}
