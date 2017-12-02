package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.person.Person;

public interface PersonRepository {


    void setPerson(Person person);

    Person getPerson();

    void updatePerson(Person person);

    void removePerson(Person person);
}
