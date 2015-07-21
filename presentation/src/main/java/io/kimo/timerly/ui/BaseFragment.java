package io.kimo.timerly.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kimo on 7/21/15.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        mapGUI(view);
        configureGUI();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopPresenter();
    }

    public abstract int getLayoutResource();
    public abstract void mapGUI(View view);
    public abstract void configureGUI();

    public abstract void startPresenter();
    public abstract void stopPresenter();
}
