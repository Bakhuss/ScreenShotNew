package ru.bakhuss.ScreenShotNew.save.dataBase;

import ru.bakhuss.ScreenShotNew.model.person.PersonalData;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PersonalDataRepo extends Repo<PersonalData> {
    void set(PersonalData personalData) throws SQLException;

    PersonalData get();

    ArrayList<PersonalData> getAll();

    void update(PersonalData personalData);

    void remove(PersonalData personalData);
}
