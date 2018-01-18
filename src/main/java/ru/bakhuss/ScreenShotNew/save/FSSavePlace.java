package ru.bakhuss.ScreenShotNew.save;

import ru.bakhuss.ScreenShotNew.save.fs.FileSystemPlace;
import ru.bakhuss.ScreenShotNew.save.impl.Place;

public class FSSavePlace extends SavePlace {

    @Override
    public Place save() {
        return new FileSystemPlace();
    }
}
