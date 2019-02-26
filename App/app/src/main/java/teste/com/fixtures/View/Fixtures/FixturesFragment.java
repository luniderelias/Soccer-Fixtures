package teste.com.fixtures.View.Fixtures;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Collections;

import teste.com.fixtures.Util.Cache;
import teste.com.fixtures.View.Base.BaseFixturesFragment;

import static teste.com.fixtures.Presenter.FixtureFragment.FixturesPresenter.FIXTURE;
import static teste.com.fixtures.View.MainActivity.sortTerm;

public class FixturesFragment extends BaseFixturesFragment implements IFixturesView {

    FixtureAdapter adapter;

    @Override
    public void setAdapter() {
        adapter = new FixtureAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    public void getData() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                fixturesPresenter.getFixturesData(getActivity(), service);
            }
        };
        thread.start();
    }

    @Override
    public void updateList() {
        if (!sortTerm.equals(""))
            sortItems();
        adapter.notifyDataSetChanged();
        setLoadingVisibility(View.GONE);
    }

    @Override
    public void sortItems() {
        Collections.sort(Cache.getFixtures(), (lhs, rhs) -> lhs.competitionStage.competition.name.toLowerCase().contains(sortTerm.toLowerCase()) ? -1 : 0);
    }

    @Override
    public void setScrollListener(){
        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);

                        String monthYearText = fixturesPresenter.getMonthYearText(getActivity(),
                                FIXTURE, ((LinearLayoutManager) recyclerView.getLayoutManager())
                                .findFirstVisibleItemPosition());

                        monthYear.setText(monthYearText);
                    }
                });
    }

}