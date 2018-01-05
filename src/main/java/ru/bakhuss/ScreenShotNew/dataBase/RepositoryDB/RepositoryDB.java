package ru.bakhuss.ScreenShotNew.dataBase.RepositoryDB;

import java.sql.SQLException;
import java.util.Collection;

public interface RepositoryDB<T> {

    void set(T... t) throws SQLException;

    Collection<T> get() throws SQLException;

    void update(T... t) throws SQLException;

    void delete(T... t) throws SQLException;

}
