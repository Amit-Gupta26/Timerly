package io.kimo.timerly.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;

import java.util.List;

import io.kimo.timerly.R;
import io.kimo.timerly.Timerly;
import io.kimo.timerly.mvp.BasePresenter;
import io.kimo.timerly.mvp.model.IntervalModel;
import io.kimo.timerly.mvp.model.TimerModel;
import io.kimo.timerly.mvp.view.CreateTimerView;

/**
 * Created by Kimo on 7/21/15.
 */
public class CreateTimerPresenter extends BasePresenter {

    private CreateTimerView createTimerView;
    private TimerModel timerModel = new TimerModel();

    public CreateTimerPresenter(Context context, CreateTimerView view) {
        super(context, view);
        this.createTimerView = view;
    }

    @Override
    public void hideAllViews() {}

    @Override
    public void destroyView() {}

    public void onAddIntervalClicked() {
        createTimerView.showAddIntervalDialog();
    }

    public void addInterval(String title, String duration, boolean halfWarning) {

        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(duration)) {
            createTimerView.showFeedback(context.getString(R.string.incomplete_interval_data));
        } else {
            IntervalModel intervalModel = new IntervalModel();
            intervalModel.setTitle(title);
            intervalModel.setDuration(Integer.parseInt(duration));
            intervalModel.setHalfWarning(halfWarning);

            timerModel.getIntervals().add(intervalModel);
            createTimerView.renderIntervals(timerModel.getIntervals());
        }


    }

    public void onCreateTimerClicked(String timerName, String numberOfLaps) {

        if(TextUtils.isEmpty(timerName) || TextUtils.isEmpty(numberOfLaps)) {
            createTimerView.showFeedback(context.getString(R.string.incomplete_timer_data));
        } else {

            timerModel.setTitle(timerName);
            timerModel.setLaps(Integer.parseInt(numberOfLaps));

            List<TimerModel> localTimers = Timerly.LOCAL_DATA.getLocalTimers();
            localTimers.add(timerModel);

            Timerly.LOCAL_DATA.setLocalTimers(localTimers);
            createTimerView.navigateToMyTimersScreen();
        }
    }
}
