package ru.bakhuss.ScreenShotNew.dataBase.Repository.DB.SQLite;

import ru.bakhuss.ScreenShotNew.dataBase.Repository.impl.PersonalDataRep;
import ru.bakhuss.ScreenShotNew.model.person.PersonalData;

import java.sql.SQLException;
import java.util.Collection;

public class SQLitePersonalData implements PersonalDataRep {
    @Override
    public void set(PersonalData... persData) throws SQLException {

    }

    @Override
    public Collection<PersonalData> get() throws SQLException {
        return null;
    }

    @Override
    public void update(PersonalData... persData) throws SQLException {

    }

    @Override
    public void delete(PersonalData... persData) throws SQLException {

    }
}
