package ru.bakhuss.ScreenShotNew.save.dataBase;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.util.ArrayList;

public interface PersonRepo extends Repo<Person> {


    void set(Person person);

    Person get();

    ArrayList<Person> getAll();

    void update(Person person);

    void remove(Person person);
}
