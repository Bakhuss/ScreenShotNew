package ru.bakhuss.ScreenShotNew.dataBase.RepositoryDB;

import ru.bakhuss.ScreenShotNew.model.person.PersonalData;

import java.sql.SQLException;
import java.util.Collection;

public class PersonalDataDB implements RepositoryDB<PersonalData> {
    @Override
    public void set(PersonalData... t) throws SQLException {

    }

    @Override
    public Collection<PersonalData> get() throws SQLException {
        return null;
    }

    @Override
    public void update(PersonalData... t) throws SQLException {

    }

    @Override
    public void delete(PersonalData... t) throws SQLException {

    }
}
