package io.kimo.timerly.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.List;

import io.kimo.timerly.R;
import io.kimo.timerly.mvp.model.TimerModel;
import io.kimo.timerly.mvp.presenter.TimerListPresenter;
import io.kimo.timerly.mvp.view.TimerListView;
import io.kimo.timerly.ui.BaseFragment;
import io.kimo.timerly.ui.activity.CreateTimerActivity;
import io.kimo.timerly.ui.activity.TimerRunnerActivity;

/**
 * Created by Kimo on 7/21/15.
 */
public class TimerListFragment extends BaseFragment implements TimerListView {

    private RecyclerView recyclerView;
    private View loadingView, emptyView;
    private TextView emptyFeedback;
    private FloatingActionButton fab;

    private TimerAdapter adapter;

    private TimerListPresenter presenter;

    @Override
    public void onResume() {
        super.onResume();

        presenter.createView();
    }

    public static TimerListFragment newInstance() {
        return new TimerListFragment();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_timer_list;
    }

    @Override
    public void mapGUI(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        loadingView = view.findViewById(R.id.view_loading);
        emptyView = view.findViewById(R.id.view_empty);
        emptyFeedback = (TextView) emptyView.findViewById(R.id.text);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    @Override
    public void configureGUI() {
        getActivity().setTitle(R.string.title_timers_screen);

        adapter = new TimerAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        fab.setImageDrawable(new IconicsDrawable(getActivity(), GoogleMaterial.Icon.gmd_add).paddingDp(4).color(Color.WHITE));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFabClicked();
            }
        });
    }

    @Override
    public void startPresenter() {
        presenter = new TimerListPresenter(getActivity(), this);
    }

    @Override
    public void stopPresenter() {
        presenter.destroyView();
    }

    @Override
    public void navigateToCreateTimerView() {
        CreateTimerActivity.navigate(getActivity());
    }

    @Override
    public void renderTimers(List<TimerModel> timers) {
        adapter.setData(timers);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showRetry(String feedback) {}

    @Override
    public void hideRetry() {}

    @Override
    public void showView() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideView() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty(String feedback) {
        emptyFeedback.setText(feedback);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        emptyView.setVisibility(View.GONE);
    }

    public class TimerAdapter extends RecyclerView.Adapter<TimerAdapter.ViewHolder> {

        private List<TimerModel> data = new ArrayList<>();

        public void setData(List<TimerModel> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_timer, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            TimerModel timerModel = data.get(i);

            viewHolder.title.setText(timerModel.getTitle());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{

            private TextView title;

            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.title);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if(getAdapterPosition() != -1) {
                    TimerRunnerActivity.navigate(getActivity(), data.get(getAdapterPosition()));
                }
            }
        }
    }
}
