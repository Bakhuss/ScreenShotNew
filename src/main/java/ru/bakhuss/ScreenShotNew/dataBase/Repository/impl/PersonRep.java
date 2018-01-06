package ru.bakhuss.ScreenShotNew.dataBase.Repository.impl;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface PersonRep extends Repository<Person> {
    @Override
    void set(Person... p) throws SQLException, IOException;

    @Override
    Collection<Person> get() throws SQLException, IOException;

    @Override
    void update(Person... p) throws SQLException, IOException;

    @Override
    void delete(Person... p) throws SQLException, IOException;
}
