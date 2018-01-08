package ru.bakhuss.ScreenShotNew.save.dataBase;

import ru.bakhuss.ScreenShotNew.model.media.Image;
import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.media.MediaGroup;
import ru.bakhuss.ScreenShotNew.model.person.Person;

public class SetAndGet {





    public void setPerson(Person person) {
    }

    public Person getPerson() {
        return new Person();
    }

    public void setMedia(Media media) {
    }

    public Media getMedia() {
        return new Image();
    }

    public void setMediaGroup(MediaGroup<?> mediaGroup) {
    }

    public MediaGroup getMediaGroup() {
        return new MediaGroup(false);
    }

}
