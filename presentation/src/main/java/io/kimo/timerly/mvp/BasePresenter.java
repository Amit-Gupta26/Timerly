package io.kimo.timerly.mvp;

import android.content.Context;

/**
 * Created by Kimo on 7/21/15.
 */
public abstract class BasePresenter implements Presenter {
    protected Context context;
    protected View view;

    public BasePresenter(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void createView() {
        hideAllViews();
    }

    public abstract void hideAllViews();
}
