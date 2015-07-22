package io.kimo.timerly.mvp.view;

import java.util.List;

import io.kimo.timerly.mvp.View;
import io.kimo.timerly.mvp.model.IntervalModel;

/**
 * Created by Kimo on 7/21/15.
 */
public interface CreateTimerView extends View {
    void showAddIntervalDialog();
    void navigateToMyTimersScreen();
    void renderIntervals(List<IntervalModel> intervals);
    void showFeedback(String msg);
}
