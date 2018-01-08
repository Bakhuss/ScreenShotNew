package ru.bakhuss.ScreenShotNew.save.db.SQLite;

import ru.bakhuss.ScreenShotNew.save.impl.PersonRep;
import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.sql.SQLException;
import java.util.*;

public class SQLitePerson implements PersonRep {

    @Override
    public void set(Person... p) throws SQLException {

    }

    @Override
    public Collection<Person> get() throws SQLException {
        return null;
    }

    @Override
    public void update(Person... p) throws SQLException {

    }

    @Override
    public void delete(Person... p) throws SQLException {

    }
}
