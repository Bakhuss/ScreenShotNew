package ru.bakhuss.ScreenShotNew.model.person;

public interface Repository <T> {
    void set(T t);
    T get();
    void update(T t);
    void remove(T t);
}
