package io.kimo.timerly.ui.fragment;

import android.view.View;

import io.kimo.timerly.R;
import io.kimo.timerly.ui.BaseFragment;

/**
 * Created by Kimo on 7/21/15.
 */
public class HistoryFragment extends BaseFragment {

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_history;
    }

    @Override
    public void mapGUI(View view) {

    }

    @Override
    public void configureGUI() {
        getActivity().setTitle(R.string.title_history_screen);
    }

    @Override
    public void startPresenter() {

    }

    @Override
    public void stopPresenter() {

    }
}
