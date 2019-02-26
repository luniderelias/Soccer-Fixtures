package teste.com.fixtures.Presenter.FixtureFragment;

import android.content.Context;

import java.util.Calendar;

import teste.com.fixtures.R;
import teste.com.fixtures.Service.FixtureService;
import teste.com.fixtures.Util.Cache;
import teste.com.fixtures.View.Base.IBaseFixturesView;

public class FixturesPresenter  implements IFixturesPresenter {

    public static final String FIXTURE = "FIXTURE";
    public static final String RESULT = "RESULT";

    IBaseFixturesView fixturesView;

    public FixturesPresenter(IBaseFixturesView fixturesView) {
        this.fixturesView = fixturesView;
    }


    @Override
    public String getMonthYearText(Context context, String fixture_type, int position) {
        Calendar cal = Calendar.getInstance();
        if(fixture_type.equals(FIXTURE))
            cal.setTime(Cache.getFixtures().get(position).date);
        else if(fixture_type.equals(RESULT))
            cal.setTime(Cache.getResults().get(position).date);

        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        return context.getResources().getStringArray(R.array.months)[month] + " " + year;

    }

    @Override
    public void getFixturesData(Context context, FixtureService service){
        service.getFixtures()
                .onErrorResumeNext(response -> {
                    fixturesView.showTryAgainSnackbar();
                }).subscribe(response -> {
            Cache.setFixtures(response);
            getMonthYearText(context,FIXTURE,0);
            fixturesView.updateList();
        }).isDisposed();
    }

    @Override
    public void getResultsData(Context context, FixtureService service){
        service.getResults()
                .onErrorResumeNext(response -> {
                    fixturesView.showTryAgainSnackbar();
                }).subscribe(response -> {
            Cache.setResults(response);
            getMonthYearText(context,RESULT,0);
            fixturesView.updateList();
        }).isDisposed();
    }
}
