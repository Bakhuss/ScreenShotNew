package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.person.PersonalData;

import java.util.ArrayList;

public class SQLitePersonalDataRepository implements PersonalDataRepository {

    SQLHandler sqlHandler;

    public SQLitePersonalDataRepository() {
        sqlHandler = new SQLHandler(DBType.sqlite);
    }

    @Override
    public void set(PersonalData personalData) {
        
    }

    @Override
    public PersonalData get() {
        return null;
    }

    @Override
    public ArrayList<PersonalData> getAll() {
        return null;
    }

    @Override
    public void update(PersonalData personalData) {

    }

    @Override
    public void remove(PersonalData personalData) {

    }
}
