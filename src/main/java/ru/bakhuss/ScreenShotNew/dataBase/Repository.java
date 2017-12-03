package ru.bakhuss.ScreenShotNew.dataBase;

public interface Repository <T> {
    void set(T t);
    T get();
    void update(T t);
    void remove(T t);
}
