package ru.bakhuss.ScreenShotNew.dataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Repo<T> {
    void set(T t) throws SQLException;
//    void setGroup(T... t) throws SQLException;
//    void set(T... t) throws SQLException;

    T get() throws SQLException;
//    ArrayList<T> getGroup() throws SQLException;

    void update(T t) throws SQLException;
//    void updateGroup(T... t) throws SQLException;
    void remove(T t) throws SQLException;

}
