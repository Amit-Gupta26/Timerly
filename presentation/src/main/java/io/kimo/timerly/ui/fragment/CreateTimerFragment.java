package io.kimo.timerly.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.kimo.timerly.R;
import io.kimo.timerly.mvp.model.IntervalModel;
import io.kimo.timerly.mvp.presenter.CreateTimerPresenter;
import io.kimo.timerly.mvp.view.CreateTimerView;
import io.kimo.timerly.ui.BaseFragment;

/**
 * Created by Kimo on 7/21/15.
 */
public class CreateTimerFragment extends BaseFragment implements CreateTimerView {

    private LinearLayout intervalsContainer;
    private View addIntervalButton;
    private EditText timerName, timerLaps, intervalName, intervalDuration;
    private SwitchCompat halfWarning;
    private AlertDialog addIntervalDialog;

    private CreateTimerPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_create_timer, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_new_timer) {
            presenter.onCreateTimerClicked(timerName.getText().toString(), timerLaps.getText().toString());
        }

        return super.onOptionsItemSelected(item);
    }

    public static CreateTimerFragment newInstance() {
        return new CreateTimerFragment();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_new_timer;
    }

    @Override
    public void mapGUI(View view) {
        timerName = (EditText) view.findViewById(R.id.timer_title);
        timerLaps = (EditText) view.findViewById(R.id.timer_laps);
        intervalsContainer = (LinearLayout) view.findViewById(R.id.intervals_container);
        addIntervalButton = view.findViewById(R.id.add_interval);
    }

    @Override
    public void configureGUI() {
        configureAddIntervalDialog();

        addIntervalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddIntervalClicked();
            }
        });
    }

    @Override
    public void startPresenter() {
        presenter = new CreateTimerPresenter(getActivity(), this);
        presenter.createView();
    }

    @Override
    public void stopPresenter() {
        presenter.destroyView();
    }

    @Override
    public void showAddIntervalDialog() {
        addIntervalDialog.show();
    }

    @Override
    public void navigateToMyTimersScreen() {
        getActivity().finish();
    }

    @Override
    public void renderIntervals(List<IntervalModel> intervals) {

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        intervalsContainer.removeAllViews();

        for(IntervalModel interval : intervals) {
            TextView intervalItem = (TextView) layoutInflater.inflate(R.layout.item_interval, intervalsContainer, false);
            intervalItem.setText(interval.getTitle() + " - " + String.format("%02d", interval.getDuration()));

            intervalsContainer.addView(intervalItem);
        }

    }

    @Override
    public void showFeedback(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showView() {}

    @Override
    public void hideView() {}

    private void configureAddIntervalDialog() {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_add_interval, null);

        intervalName = (EditText) view.findViewById(R.id.interval_title);
        intervalDuration = (EditText) view.findViewById(R.id.interval_duration);
        halfWarning = (SwitchCompat) view.findViewById(R.id.interval_half_warning);

        addIntervalDialog = new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.new_interval))
                .setView(view)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        presenter.addInterval(intervalName.getText().toString(), intervalDuration.getText().toString(), halfWarning.isChecked());

                        intervalName.setText("");
                        intervalDuration.setText("");
                        halfWarning.setChecked(false);

                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intervalName.setText("");
                        intervalDuration.setText("");
                        halfWarning.setChecked(false);
                        dialog.dismiss();
                    }
                })
                .create();
    }
}
