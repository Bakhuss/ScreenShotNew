package ru.bakhuss.ScreenShotNew.save.impl.sql;

import ru.bakhuss.ScreenShotNew.save.dataBase.DBType;
import ru.bakhuss.ScreenShotNew.save.dataBase.DataBaseFile;
import ru.bakhuss.ScreenShotNew.save.dataBase.SQLHandler;

public abstract class SQL {
    SQLHandler handler;
    DBType dbType;

    public SQL() {
        dbType = DataBaseFile.getDbType();
    }

    public abstract void createRepository();
}
