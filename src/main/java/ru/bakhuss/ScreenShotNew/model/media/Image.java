package ru.bakhuss.ScreenShotNew.model.media;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.awt.image.BufferedImage;
import java.util.*;

public class Image extends Media {

    private int imageId;

    public Image() {}

    public Image(Long dateIn, BufferedImage image) {
        super.dateIn = dateIn;
        super.media = image;
    }


    @Override
    public BufferedImage getMedia() {
        return super.media;
    }

    @Override
    public void setMedia(BufferedImage image) {
        super.media = image;
    }

    @Override
    public String getName() {
        return super.name;
    }

    @Override
    public void setName(String imgName) {
        super.name = imgName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
