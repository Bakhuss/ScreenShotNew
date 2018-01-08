package ru.bakhuss.ScreenShotNew.model.media;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Media extends MediaAbstract {

//    private static HashMap<Long, Image> commonImageList = null;
    private static HashMap<Long, Audio> commonAudioList = null;
    private static HashMap<Long, Video> commonVideoList = null;
    private static HashMap<Long, ? extends Media> commonMediaList = null;
    private static ConcurrentHashMap<Long, BufferedImage> commonImageList = null;

    protected Long nameId = null;
    protected BufferedImage media = null;

    protected Media(){}

    public Long getNameId() {
        return nameId;
    }

    public void setNameId(Long nameId) {
        this.nameId = nameId;
    }

    public static ConcurrentHashMap<Long, BufferedImage> getCommonImageList() {
        if (commonImageList == null) commonImageList = new ConcurrentHashMap<>();
        return commonImageList;
    }

    public static HashMap<Long, Audio> getCommonAudioList() {
        if (commonAudioList == null) commonAudioList = new HashMap<>();
        return commonAudioList;
    }

    public static HashMap<Long, Video> getCommonVideoList() {
        if (commonVideoList == null) commonVideoList = new HashMap<>();
        return commonVideoList;
    }

    public static HashMap<Long, ? extends Media> getCommonMediaList() {
        return commonMediaList;
    }

}
