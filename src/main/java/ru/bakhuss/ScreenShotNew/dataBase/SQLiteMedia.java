package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.media.Media;

import java.sql.SQLException;

public class SQLiteMedia implements MediaRepository {

    SQLHandler sqlHandler;

    public SQLiteMedia() {
        sqlHandler = new SQLHandler(DBType.sqlite);
    }

    public void set(Media<? extends Media> media) {
        try {
            sqlHandler.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqlHandler.disconnect();
        }
    }

    public Media<? extends Media> get() {
        return null;
    }

    public void update(Media<? extends Media> media) {

    }

    public void remove(Media<? extends Media> media) {

    }
}
