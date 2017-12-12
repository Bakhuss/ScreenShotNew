package ru.bakhuss.ScreenShotNew.dataBase;

import java.util.ArrayList;

public interface Repository <T> {
    void set(T t);
    T get();
    ArrayList<T> getAll();
    void update(T t);
    void remove(T t);
}
