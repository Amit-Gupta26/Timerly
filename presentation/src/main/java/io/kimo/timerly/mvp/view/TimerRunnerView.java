package io.kimo.timerly.mvp.view;

import io.kimo.timerly.mvp.View;

/**
 * Created by Kimo on 7/22/15.
 */
public interface TimerRunnerView extends View {
    void updateLaps(String value);
    void updateIntervalTitle(String value);
    void updateStartButtonText(String value);
    void startCounter();
    void pauseCounter();
    void resetCounter();
}
