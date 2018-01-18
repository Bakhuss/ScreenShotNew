package ru.bakhuss.ScreenShotNew.save.impl;

import ru.bakhuss.ScreenShotNew.save.FSSavePlace;
import ru.bakhuss.ScreenShotNew.save.SQLSavePlace;
import ru.bakhuss.ScreenShotNew.save.SavePlace;
import ru.bakhuss.ScreenShotNew.save.fs.FileSystemPlace;
import ru.bakhuss.ScreenShotNew.save.impl.sql.SQLPlace;

public class SaveMethodFactory {

    public SavePlace getSavePlace(Place place) {

        SavePlace savePlace = null;

        if (place instanceof SQLPlace){
            System.out.println("sql save place");
            savePlace = new SQLSavePlace();
        }
        if (place instanceof FileSystemPlace){
            System.out.println("fs save place");
            savePlace = new FSSavePlace();
        }
        if (place instanceof NetPlace) System.out.println("net save place");

        return savePlace;
    }

}
