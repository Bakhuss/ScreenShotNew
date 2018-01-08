package ru.bakhuss.ScreenShotNew.save.db.SQLite;

import ru.bakhuss.ScreenShotNew.save.impl.repository.MediaRep;
import ru.bakhuss.ScreenShotNew.model.media.Media;

import java.sql.SQLException;
import java.util.Collection;

public class SQLiteMedia implements MediaRep {

    @Override
    public void set(Media... m) throws SQLException {

    }

    @Override
    public Collection<Media> get() throws SQLException {
        return null;
    }

    @Override
    public void update(Media... m) throws SQLException {

    }

    @Override
    public void delete(Media... m) throws SQLException {

    }
}
