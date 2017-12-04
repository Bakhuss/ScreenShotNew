package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.media.Media;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteMedia implements MediaRepository {

    SQLHandler sqlHandler;

    public SQLiteMedia() {
        sqlHandler = new SQLHandler(DBType.sqlite);
    }

    @Override
    public void set(Media media) {
        try {
            sqlHandler.connect();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqlHandler.disconnect();
        }
    }

    @Override
    public Media get() {
        return null;
    }

    @Override
    public void update(Media media) {

    }

    @Override
    public void remove(Media media) {

    }





/*
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
*/

}
