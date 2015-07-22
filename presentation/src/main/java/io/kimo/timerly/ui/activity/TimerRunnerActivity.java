package io.kimo.timerly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;

import io.kimo.timerly.R;
import io.kimo.timerly.mvp.model.TimerModel;
import io.kimo.timerly.ui.BaseActivity;
import io.kimo.timerly.ui.fragment.TimerRunnerFragment;

/**
 * Created by Kimo on 7/22/15.
 */
public class TimerRunnerActivity extends BaseActivity {

    public static final String TAG = TimerRunnerActivity.class.getSimpleName();
    public static final String TIMER = TAG + ".TIMER";

    private TimerModel timerModel;

    public static void navigate(Context context, TimerModel timer) {
        Intent intentToBeCalled = new Intent(context, TimerRunnerActivity.class);
        intentToBeCalled.putExtra(TIMER, new Gson().toJson(timer));

        context.startActivity(intentToBeCalled);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle args = getIntent().getExtras();

        if(args != null) {
            timerModel = new Gson().fromJson(args.getString(TIMER), TimerModel.class);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_with_toolbar;
    }

    @Override
    public Fragment getMainFragment() {
        return TimerRunnerFragment.newInstance(timerModel);
    }
}
