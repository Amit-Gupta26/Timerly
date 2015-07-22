package io.kimo.timerly.mvp.presenter;

import android.content.Context;

import io.kimo.timerly.R;
import io.kimo.timerly.mvp.BasePresenter;
import io.kimo.timerly.mvp.model.IntervalModel;
import io.kimo.timerly.mvp.model.TimerModel;
import io.kimo.timerly.mvp.view.TimerRunnerView;

/**
 * Created by Kimo on 7/22/15.
 */
public class TimerRunnerPresenter extends BasePresenter{

    private TimerRunnerView timerRunnerView;
    private TimerModel timerModel;
    private boolean isCounting = false;
    private IntervalModel currentInterval;

    public TimerRunnerPresenter(Context context, TimerRunnerView view, TimerModel timerModel) {
        super(context, view);
        this.timerRunnerView = view;
        this.timerModel = timerModel;
        currentInterval = timerModel.getIntervals().get(0);
    }

    @Override
    public void createView() {
        super.createView();

        timerRunnerView.updateLaps(String.format("%02d/%02d", 0, timerModel.getLaps()));
        timerRunnerView.updateIntervalTitle(timerModel.getIntervals().get(0).getTitle());
    }

    @Override
    public void hideAllViews() {}

    @Override
    public void destroyView() {}

    public void startCounting() {
        if(isCounting) {
            timerRunnerView.updateStartButtonText(context.getString(R.string.start));
            timerRunnerView.pauseCounter();
            isCounting = false;
        } else {
            timerRunnerView.updateStartButtonText(context.getString(R.string.pause));
            timerRunnerView.startCounter();
            isCounting = true;
        }
    }

    public void stopCounting() {
        isCounting = false;

        timerRunnerView.updateStartButtonText(context.getString(R.string.start));
        timerRunnerView.pauseCounter();
        timerRunnerView.resetCounter();
    }
}
