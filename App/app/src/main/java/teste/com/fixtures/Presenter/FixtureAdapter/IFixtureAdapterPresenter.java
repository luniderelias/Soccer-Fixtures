package teste.com.fixtures.Presenter.FixtureAdapter;

import android.content.Context;

import teste.com.fixtures.Model.Fixture;
import teste.com.fixtures.Model.Result;

public interface IFixtureAdapterPresenter {

    String getFixtureDateString(Fixture fixture);
    String getWeekday(Context context, Fixture fixture);

    String getResultDateString(Result result);
}
