package ru.bakhuss.ScreenShotNew.model.person;

public interface PersonRep extends Repository<Person> {

    void set(Person person);

    Person get();

    void update(Person person);

    void remove(Person person);
}
