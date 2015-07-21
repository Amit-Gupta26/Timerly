package io.kimo.timerly.mvp.view;

import io.kimo.timerly.mvp.View;

/**
 * Created by Kimo on 7/21/15.
 */
public interface CreateTimerView extends View {
    void showAddIntervalDialog();
    void navigateToMyTimersScreen();
}
