package ru.bakhuss.ScreenShotNew.save;

import ru.bakhuss.ScreenShotNew.save.impl.Repository;

public abstract class SavePlace implements Repository {
    Repository repository;

    public SavePlace() {
    }

}
