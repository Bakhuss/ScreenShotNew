package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.person.Person;

public interface PersonRepository extends Repository<Person> {


    void set(Person person);

    Person get();

    void update(Person person);

    void remove(Person person);
}
