package ru.bakhuss.ScreenShotNew.dataBase.RepositoryDB;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class SQLitePerson implements PersonDB {
    @Override
    public void set(Person... p) throws SQLException {
        if (p.length == 1) System.out.println(1);
        else System.out.println(2);
    }

    @Override
    public Collection<Person> get() throws SQLException {
        return new ArrayList<>();
    }

    @Override
    public void update(Person... p) throws SQLException {

    }

    @Override
    public void delete(Person... p) throws SQLException {

    }
}
