package ru.bakhuss.ScreenShotNew.model;

public class AbstractFields {
    protected Long id = null;
    protected Long dateIn = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDateIn() {
        return dateIn;
    }

    public void setDateIn(Long dateIn) {
        this.dateIn = dateIn;
    }
}
