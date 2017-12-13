package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.person.PersonalData;

import java.util.ArrayList;

public interface PersonalDataRepository extends Repository<PersonalData> {
    void set(PersonalData personalData);

    PersonalData get();

    ArrayList<PersonalData> getAll();

    void update(PersonalData personalData);

    void remove(PersonalData personalData);
}
