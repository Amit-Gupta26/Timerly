package io.kimo.timerly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import io.kimo.timerly.R;
import io.kimo.timerly.ui.BaseActivity;
import io.kimo.timerly.ui.fragment.CreateTimerFragment;

/**
 * Created by Kimo on 7/21/15.
 */
public class CreateTimerActivity extends BaseActivity {

    public static void navigate(Context context) {
        Intent intentToBeCalled = new Intent(context, CreateTimerActivity.class);
        context.startActivity(intentToBeCalled);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_with_toolbar;
    }

    @Override
    public Fragment getMainFragment() {
        return CreateTimerFragment.newInstance();
    }
}
