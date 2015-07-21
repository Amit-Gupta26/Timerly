package io.kimo.timerly.ui.fragment;

import android.view.View;

import io.kimo.timerly.R;
import io.kimo.timerly.mvp.view.CreateTimerView;
import io.kimo.timerly.ui.BaseFragment;

/**
 * Created by Kimo on 7/21/15.
 */
public class CreateTimerFragment extends BaseFragment implements CreateTimerView {

    public static CreateTimerFragment newInstance() {
        return new CreateTimerFragment();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_new_timer;
    }

    @Override
    public void mapGUI(View view) {

    }

    @Override
    public void configureGUI() {

    }

    @Override
    public void startPresenter() {

    }

    @Override
    public void stopPresenter() {

    }

    @Override
    public void showAddIntervalDialog() {

    }

    @Override
    public void navigateToMyTimersScreen() {

    }

    @Override
    public void showView() {

    }

    @Override
    public void hideView() {

    }
}
