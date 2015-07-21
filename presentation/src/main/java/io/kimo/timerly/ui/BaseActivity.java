package io.kimo.timerly.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.kimo.timerly.R;

/**
 * Created by Kimo on 7/21/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        configureToolbar();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, getMainFragment())
                .commit();
    }

    public abstract int getLayoutResource();
    public abstract Fragment getMainFragment();

    private void configureToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
