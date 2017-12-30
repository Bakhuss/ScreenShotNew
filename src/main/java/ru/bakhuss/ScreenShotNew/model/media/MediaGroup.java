package ru.bakhuss.ScreenShotNew.model.media;

import java.util.ArrayList;
import java.util.List;

public class MediaGroup<T extends Media> {

    private List<T> mediaGroup = null;
    private String groupName = null;
    private int groupNameId = 0;
    private boolean autoscreen = false;
    private T obj = null;

    public MediaGroup(boolean autoscreen) {
        this.mediaGroup = new ArrayList<>();
        this.autoscreen = autoscreen;
    }

    public List<T> getMediaList() {
        return mediaGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupNameId() {
        return groupNameId;
    }

    public void setGroupNameId(int groupNameId) {
        this.groupNameId = groupNameId;
    }

    public boolean isAutoscreen() {
        return autoscreen;
    }

    public String getMediaGroupClass() {
        return obj.getClass().getSimpleName();
    }
}
