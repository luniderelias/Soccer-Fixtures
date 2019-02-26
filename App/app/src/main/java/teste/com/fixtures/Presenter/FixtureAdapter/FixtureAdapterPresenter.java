package teste.com.fixtures.Presenter.FixtureAdapter;

import android.content.Context;

import java.util.Calendar;
import java.util.Locale;

import teste.com.fixtures.Model.Fixture;
import teste.com.fixtures.Model.Result;
import teste.com.fixtures.R;
import teste.com.fixtures.Util.DateUtil;
import teste.com.fixtures.View.Fixtures.FixtureAdapter;

import static android.view.View.VISIBLE;

public class FixtureAdapterPresenter implements IFixtureAdapterPresenter{



    public FixtureAdapterPresenter() {
    }

    @Override
    public String getFixtureDateString(Fixture fixture) {
        String date = DateUtil.getModifiedDate(Locale.getDefault(), fixture.date.getTime());
        String venueAndDate = fixture.venue.name + " | ";
        if (fixture.state != null && fixture.state.equals("postponed"))
            venueAndDate += "<font color=#AA2211>" + date + "</font>";
         else
            venueAndDate += date;

        return venueAndDate;
    }


    @Override
    public String getWeekday(Context context, Fixture fixture) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fixture.date);
        return cal.get(Calendar.DATE) + "\n" + context.getResources().getStringArray(R.array.weekdays)[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }


    @Override
    public String getResultDateString(Result result) {
        String date = DateUtil.getModifiedDate(Locale.getDefault(), result.date.getTime());
        return result.venue.name + " | " + date;
    }
}
