package ru.bakhuss.ScreenShotNew.model.media;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Image extends Media {

    // K - id, V - BufferedImage
    private static HashMap<Long, Image> commonImageList = null;

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

//    public static HashMap<Long, Image> getCommonImageList() {
//        if (commonImageList == null) commonImageList = new HashMap<>();
//        return commonImageList;
//    }
}
