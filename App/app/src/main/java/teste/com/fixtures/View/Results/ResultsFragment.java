package teste.com.fixtures.View.Results;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Collections;

import teste.com.fixtures.Util.Cache;
import teste.com.fixtures.View.Fixtures.FixturesFragment;

import static teste.com.fixtures.Presenter.FixtureFragment.FixturesPresenter.RESULT;
import static teste.com.fixtures.View.MainActivity.sortTerm;

public class ResultsFragment extends FixturesFragment implements IResultsView{

    ResultAdapter adapter;

    @Override
    public void setAdapter() {
        adapter = new ResultAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }


    public void getData() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                fixturesPresenter.getResultsData(getActivity(), service);
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
        Collections.sort(Cache.getResults(), (lhs, rhs) -> lhs.competitionStage.competition.name.toLowerCase().contains(sortTerm.toLowerCase()) ? -1 : 0);
    }


    @Override
    public void setScrollListener(){
        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);

                        String monthYearText = fixturesPresenter.getMonthYearText(getActivity(),
                                RESULT,
                                ((LinearLayoutManager) recyclerView.getLayoutManager())
                                .findFirstVisibleItemPosition());

                        monthYear.setText(monthYearText);
                    }
                });
    }


}