package io.kimo.timerly.mvp.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.kimo.lib.faker.Faker;
import io.kimo.timerly.R;
import io.kimo.timerly.mvp.BasePresenter;
import io.kimo.timerly.mvp.model.TimerModel;
import io.kimo.timerly.mvp.view.TimerListView;

/**
 * Created by Kimo on 7/21/15.
 */
public class TimerListPresenter extends BasePresenter{

    private TimerListView timerListView;

    public TimerListPresenter(Context context, TimerListView view) {
        super(context, view);
        this.timerListView = view;
    }

    @Override
    public void createView() {
        super.createView();

        reloadTimers();
    }

    @Override
    public void hideAllViews() {
        timerListView.hideLoading();
        timerListView.hideRetry();
        timerListView.hideView();
        timerListView.hideEmpty();
    }

    @Override
    public void destroyView() {}

    public void reloadTimers() {
        hideAllViews();
        timerListView.showLoading();

        List<TimerModel> fakeTimers = new ArrayList<>();
//        List<TimerModel> fakeTimers = generateFakeTimers(0);

        if(fakeTimers.isEmpty()) {
            timerListView.showEmpty(context.getString(R.string.empty_timers_feedback));
        } else {
            timerListView.renderTimers(fakeTimers);
            timerListView.showView();
        }

        timerListView.hideLoading();
    }

    public void onFabClicked() {
        timerListView.navigateToCreateTimerView();
    }

    private List<TimerModel> generateFakeTimers(int numberOfTimers) {

        List<TimerModel> timers = new ArrayList<>();

        for(int i = 0; i < numberOfTimers; i++) {
            TimerModel timerModel = new TimerModel();

            timerModel.setColor(Faker.with(context).Color.randomColor());
            timerModel.setTitle(Faker.with(context).Name.firstName() + " #" + Faker.with(context).Number.positiveDigit());

            timers.add(timerModel);
        }

        return timers;

    }
}
