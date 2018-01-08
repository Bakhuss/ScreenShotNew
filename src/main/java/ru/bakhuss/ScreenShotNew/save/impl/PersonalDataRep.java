package ru.bakhuss.ScreenShotNew.save.impl;

import ru.bakhuss.ScreenShotNew.model.person.PersonalData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface PersonalDataRep extends Repository<PersonalData> {
    @Override
    void set(PersonalData... persData) throws SQLException, IOException;

    @Override
    Collection<PersonalData> get() throws SQLException, IOException;

    @Override
    void update(PersonalData... persData) throws SQLException, IOException;

    @Override
    void delete(PersonalData... persData) throws SQLException, IOException;
}
