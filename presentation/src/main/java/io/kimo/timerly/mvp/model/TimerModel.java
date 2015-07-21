package io.kimo.timerly.mvp.model;

import io.kimo.timerly.mvp.BaseModel;

/**
 * Created by Kimo on 7/21/15.
 */
public class TimerModel extends BaseModel {
    private int color;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
