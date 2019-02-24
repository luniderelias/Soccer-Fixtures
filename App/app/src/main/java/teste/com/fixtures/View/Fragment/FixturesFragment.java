package teste.com.fixtures.View.Fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;
import java.util.Collections;

import teste.com.fixtures.View.Adapter.FixtureAdapter;
import teste.com.fixtures.R;
import teste.com.fixtures.Service.FixtureService;
import teste.com.fixtures.Util.Cache;
import teste.com.fixtures.Util.ConnectivityUtil;

import static teste.com.fixtures.View.Activity.MainActivity.sortTerm;

@EFragment(R.layout.fragment_fixtures)
public class FixturesFragment extends Fragment {

    @ViewById(R.id.recyclerView)
    RecyclerView recyclerView;

    @ViewById(R.id.monthYear)
    TextView monthYear;

    @ViewById(R.id.loadingImageView)
    ImageView loadingImageView;

    FixtureAdapter adapter;

    @Bean
    FixtureService service;

    @TextChange(R.id.searchEditText)
    public void onTextChange(CharSequence text) {
        sortTerm = text.toString();
        updateList();
    }

    @AfterViews
    public void afterViews() {

        showLoading();
        configureRecyclerView();
        setAdapter();
        getData();
        setScrollListener();
    }

    private void showLoading(){
        Glide.with(getActivity())
                .load(R.drawable.loading)
                .placeholder(R.drawable.loading)
                .into(loadingImageView);
        loadingImageView.setVisibility(View.VISIBLE);
    }

    private void configureRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    void setAdapter() {
        adapter = new FixtureAdapter(getActivity(), Cache.getFixtures());
        recyclerView.setAdapter(adapter);
    }


    @Background
    void getData() {
        checkConnectivity();

        service.getFixtures()
                .onErrorResumeNext(response -> {
                    showTryAgainSnackbar();
                }).subscribe(response -> {
            Cache.setFixtures(response);
            setMonthYear(0);
            updateList();
        }).isDisposed();

    }

    @UiThread
    void checkConnectivity() {
        if (!ConnectivityUtil.hasNetworkConnection(getActivity()))
            Snackbar.make(recyclerView,
                    getString(R.string.not_connected),
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.connect, view -> startActivityForResult(new Intent(
                            android.provider.Settings.ACTION_SETTINGS), 0))
                    .setActionTextColor(getResources()
                            .getColor(R.color.light_blue)).show();
    }

    @UiThread
    void showTryAgainSnackbar() {
        Snackbar.make(recyclerView,
                getString(R.string.connection_failed),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.try_again, view -> getData())
                .setActionTextColor(getResources()
                        .getColor(R.color.light_blue)).show();
    }


    public void setMonthYear(int position) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Cache.getFixtures().get(position).date);

        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        String monthAndYear = getActivity().getResources().getStringArray(R.array.months)[month] + " " + year;
        monthYear.setText(monthAndYear);
    }


    private void updateList() {
        adapter.setFixtures(Cache.getFixtures());
        if (!sortTerm.equals(""))
            sortItems();
        adapter.notifyDataSetChanged();
        loadingImageView.setVisibility(View.GONE);
    }

    public void sortItems() {
            Collections.sort(Cache.getFixtures(), (lhs, rhs) -> lhs.competitionStage.competition.name.toLowerCase().contains(sortTerm.toLowerCase()) ? -1 : 0);
    }

    private void setScrollListener() {
        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);

                        setMonthYear(((LinearLayoutManager) recyclerView.getLayoutManager())
                                .findFirstVisibleItemPosition());
                    }
                });
    }

}