package io.kimo.timerly.mvp;

/**
 * Created by Kimo on 7/21/15.
 */
public interface CollectionView extends View {
    void showEmpty(String feedback);
    void hideEmpty();
}
