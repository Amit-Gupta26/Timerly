package io.kimo.timerly.mvp.model;

import io.kimo.timerly.mvp.BaseModel;

/**
 * Created by Kimo on 7/21/15.
 */
public class IntervalModel extends BaseModel {
    private String title;
    private int duration;
    private boolean halfWarning;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isHalfWarning() {
        return halfWarning;
    }

    public void setHalfWarning(boolean halfWarning) {
        this.halfWarning = halfWarning;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
