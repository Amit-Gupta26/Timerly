package io.kimo.timerly.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import io.kimo.timerly.R;
import io.kimo.timerly.ui.fragment.HistoryFragment;
import io.kimo.timerly.ui.fragment.TimerListFragment;


public class MainActivity extends AppCompatActivity {

    public static final int FRAGMENT_TIMER_LIST = 0;
    public static final int FRAGMENT_HISTORY = 1;

    private Toolbar toolbar;
    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar);

        configureToolbar();
        configureDrawer(savedInstanceState);

        if(savedInstanceState == null) {
            showFragment(FRAGMENT_TIMER_LIST);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if(drawer != null) {
            outState = drawer.saveInstanceState(outState);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if(drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void configureToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void configureDrawer(Bundle savedInstanceState) {

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.header_drawer)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                            .withName(R.string.title_timers_screen)
                            .withIcon(GoogleMaterial.Icon.gmd_alarm)
                            .withSelectedIconColorRes(R.color.primary)
                            .withSelectedTextColorRes(R.color.primary)
                            .withIdentifier(FRAGMENT_TIMER_LIST),
                        new PrimaryDrawerItem()
                            .withName(R.string.title_history_screen)
                            .withIcon(GoogleMaterial.Icon.gmd_history)
                            .withSelectedIconColorRes(R.color.primary)
                            .withSelectedTextColorRes(R.color.primary)
                            .withIdentifier(FRAGMENT_HISTORY)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {

                        if(iDrawerItem != null) {
                            showFragment(iDrawerItem.getIdentifier());
                        }

                        return false;
                    }
                })
                .withSelectedItem(FRAGMENT_TIMER_LIST)
                .withSavedInstance(savedInstanceState)
                .build();
    }

    private void showFragment(int flag) {
        switch (flag) {
            case FRAGMENT_TIMER_LIST:
                replace(TimerListFragment.newInstance());
                break;
            case FRAGMENT_HISTORY:
                replace(HistoryFragment.newInstance());
                break;
        }
    }

    private void replace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
