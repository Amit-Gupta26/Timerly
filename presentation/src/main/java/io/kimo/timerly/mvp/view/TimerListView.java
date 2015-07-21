package io.kimo.timerly.mvp.view;

import java.util.List;

import io.kimo.timerly.mvp.CollectionView;
import io.kimo.timerly.mvp.LoadDataView;
import io.kimo.timerly.mvp.model.TimerModel;

/**
 * Created by Kimo on 7/21/15.
 */
public interface TimerListView extends LoadDataView, CollectionView {
    void navigateToCreateTimerView();
    void renderTimers(List<TimerModel> timers);
}
