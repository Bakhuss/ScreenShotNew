package ru.bakhuss.ScreenShotNew.dataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Repository <T> {
    void set(T t) throws SQLException;
    void setGroup(T t) throws SQLException;
    T get() throws SQLException;
    ArrayList<T> getAll() throws SQLException;
    void update(T t) throws SQLException;
    void remove(T t) throws SQLException;
}
