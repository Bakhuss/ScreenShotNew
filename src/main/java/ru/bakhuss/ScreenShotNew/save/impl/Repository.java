package ru.bakhuss.ScreenShotNew.save.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface Repository<T> {
    void set(T... ts) throws SQLException, IOException;
    Collection<T> get() throws SQLException, IOException;
    void update(T... ts) throws SQLException, IOException;
    void delete(T... ts) throws SQLException, IOException;
}