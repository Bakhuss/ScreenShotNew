package ru.bakhuss.ScreenShotNew.model.media;

import java.util.ArrayList;
import java.util.List;

public class MediaGroup<T extends Media> {

    private List<T> mediaGroup = null;
    private String groupName = null;
    private int groupNameId = 0;

    public MediaGroup() {
        this.mediaGroup = new ArrayList<>();
    }

    public List<T> getMediaGroup() {
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
}
