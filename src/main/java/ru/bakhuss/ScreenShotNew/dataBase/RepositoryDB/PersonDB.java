package ru.bakhuss.ScreenShotNew.dataBase.RepositoryDB;

import ru.bakhuss.ScreenShotNew.model.person.Person;
import java.sql.SQLException;
import java.util.Collection;

public interface PersonDB extends RepositoryDB<Person> {
    @Override
    void set(Person... p) throws SQLException;

    @Override
    Collection<Person> get() throws SQLException;

    @Override
    void update(Person... p) throws SQLException;

    @Override
    void delete(Person... p) throws SQLException;
}
