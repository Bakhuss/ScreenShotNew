package ru.bakhuss.ScreenShotNew.save;

import ru.bakhuss.ScreenShotNew.save.impl.repository.Repository;
import ru.bakhuss.ScreenShotNew.save.impl.sql.SQL;

public abstract class SavePlace implements Repository {
    SQL sql;

    public SavePlace() {}

}
