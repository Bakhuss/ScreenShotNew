package ru.bakhuss.ScreenShotNew.save;

import ru.bakhuss.ScreenShotNew.save.impl.Place;
import ru.bakhuss.ScreenShotNew.save.impl.sql.SQLPlace;

public class SQLSavePlace extends SavePlace {

    @Override
    public Place save() {
        return new SQLPlace();
    }
}
