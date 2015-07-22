package io.kimo.timerly.mvp.model;

import java.util.ArrayList;
import java.util.List;

import io.kimo.timerly.mvp.BaseModel;

/**
 * Created by Kimo on 7/21/15.
 */
public class TimerModel extends BaseModel {
    private String title;
    private int laps;
    private List<IntervalModel> intervals = new ArrayList<>();

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<IntervalModel> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<IntervalModel> intervals) {
        this.intervals = intervals;
    }
}
