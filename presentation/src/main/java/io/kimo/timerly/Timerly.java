package io.kimo.timerly;

import android.app.Application;

import java.util.List;

import io.kimo.timerly.data.LocalData;
import io.kimo.timerly.mvp.model.IntervalModel;
import io.kimo.timerly.mvp.model.TimerModel;

/**
 * Created by Kimo on 7/21/15.
 */
public class Timerly extends Application {
    public static LocalData LOCAL_DATA;

    @Override
    public void onCreate() {
        super.onCreate();

        configureSharedPreferences();
        configureDefaultTimers();
    }

    private void configureSharedPreferences() {
        if(LOCAL_DATA == null) {
            LOCAL_DATA = new LocalData(this);
        }
    }

    private void configureDefaultTimers() {
        TimerModel tabata = new TimerModel();

        tabata.setTitle("Tabata");
        tabata.setLaps(30);

        IntervalModel work = new IntervalModel();
        work.setTitle("Work");
        work.setDuration(20);

        IntervalModel rest = new IntervalModel();
        rest.setTitle("Rest");
        rest.setDuration(10);

        tabata.getIntervals().add(work);
        tabata.getIntervals().add(rest);

        TimerModel hiit = new TimerModel();

        hiit.setTitle("HITT");
        hiit.setLaps(15);

        work.setDuration(10);
        rest.setDuration(10);

        hiit.getIntervals().add(work);
        hiit.getIntervals().add(rest);

        work.setDuration(20);
        rest.setDuration(20);

        hiit.getIntervals().add(work);
        hiit.getIntervals().add(rest);

        work.setDuration(30);
        rest.setDuration(30);

        hiit.getIntervals().add(work);
        hiit.getIntervals().add(rest);

        List<TimerModel> localTimers = LOCAL_DATA.getLocalTimers();

        localTimers.add(tabata);
        localTimers.add(hiit);

        LOCAL_DATA.setLocalTimers(localTimers);
    }
}
