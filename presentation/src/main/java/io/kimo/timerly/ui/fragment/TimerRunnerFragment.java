package io.kimo.timerly.ui.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.gson.Gson;

import io.kimo.timerly.R;
import io.kimo.timerly.mvp.model.TimerModel;
import io.kimo.timerly.mvp.presenter.TimerRunnerPresenter;
import io.kimo.timerly.mvp.view.TimerRunnerView;
import io.kimo.timerly.ui.BaseFragment;

/**
 * Created by Kimo on 7/22/15.
 */
public class TimerRunnerFragment extends BaseFragment implements TimerRunnerView {

    public static final String TAG = TimerRunnerFragment.class.getSimpleName();
    public static final String TIMER = TAG + ".TIMER";

    private TextView laps, interval;
    private Chronometer chronometer;
    private Button startOrReset, stop;

    private TimerRunnerPresenter presenter;

    private TimerModel timerModel;

    public static TimerRunnerFragment newInstance(TimerModel timer) {
        TimerRunnerFragment fragment = new TimerRunnerFragment();

        Bundle args = new Bundle();
        args.putString(TIMER, new Gson().toJson(timer));

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Bundle args = getArguments();

        if(args != null) {
            timerModel = new Gson().fromJson(args.getString(TIMER), TimerModel.class);
        }

        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_timer_runner, menu);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_timer_runner;
    }

    @Override
    public void mapGUI(View view) {
        laps = (TextView) view.findViewById(R.id.laps);
        interval = (TextView) view.findViewById(R.id.interval_title);
        chronometer = (Chronometer) view.findViewById(R.id.chronometer);
        startOrReset = (Button) view.findViewById(R.id.start);
        stop = (Button) view.findViewById(R.id.stop);
    }

    @Override
    public void configureGUI() {
        getActivity().setTitle(timerModel.getTitle());

        startOrReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startCounting();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.stopCounting();
            }
        });
    }

    @Override
    public void startPresenter() {
        presenter = new TimerRunnerPresenter(getActivity(), this, timerModel);
        presenter.createView();
    }

    @Override
    public void stopPresenter() {
        presenter.destroyView();
    }

    @Override
    public void updateLaps(String value) {
        laps.setText(value);
    }

    @Override
    public void updateIntervalTitle(String value) {
        interval.setText(value);
    }

    @Override
    public void updateStartButtonText(String value) {
        startOrReset.setText(value);
    }

    @Override
    public void startCounter() {
        chronometer.start();
    }

    @Override
    public void pauseCounter() {
        chronometer.stop();
    }

    @Override
    public void resetCounter() {
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    @Override
    public void showView() {

    }

    @Override
    public void hideView() {

    }
}
