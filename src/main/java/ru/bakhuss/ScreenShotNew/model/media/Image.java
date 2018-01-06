package ru.bakhuss.ScreenShotNew.model.media;

import java.awt.image.BufferedImage;

public class Image extends Media {

    public Image() {}

    public Image(Long dateIn, BufferedImage img) {
        super.dateIn = dateIn;
        super.media = img;
    }

    public BufferedImage getImage() {
        return super.media;
    }

    public void setImage(BufferedImage img) {
        super.media = img;
    }
}
