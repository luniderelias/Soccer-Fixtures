package teste.com.fixtures.Presenter.FixtureFragment;

import android.content.Context;

import teste.com.fixtures.Service.FixtureService;

public interface IFixturesPresenter {

    String getMonthYearText(Context context, String fixture_type, int position);
    void getFixturesData(Context context, FixtureService service);
    void getResultsData(Context context, FixtureService service);
}
