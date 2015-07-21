package io.kimo.timerly.mvp;

/**
 * Created by Kimo on 7/21/15.
 */
public interface LoadDataView extends View {
    void showLoading();
    void hideLoading();

    void showRetry(String feedback);
    void hideRetry();
}
