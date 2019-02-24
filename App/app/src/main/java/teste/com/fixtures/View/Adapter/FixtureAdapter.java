package teste.com.fixtures.View.Adapter;

import teste.com.fixtures.Model.Fixture;
import teste.com.fixtures.R;
import teste.com.fixtures.Util.DateUtil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.view.View.VISIBLE;
import static teste.com.fixtures.Util.DrawableUtil.getShield;

public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.ViewHolder> {

    private List<Fixture> fixtures = new ArrayList<>();
    private Context context;

    public FixtureAdapter(Context context, List<Fixture> fixtures) {
        this.fixtures.addAll(fixtures);
        this.context = context;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixture_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return fixtures.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setIsRecyclable(false);

        Fixture fixture = fixtures.get(position);

        holder.competitionName.setText(fixture.competitionStage.competition.name);
        holder.hostName.setText(fixture.homeTeam.name);
        holder.awayName.setText(fixture.awayTeam.name);
        holder.venueAndDate.setText(Html.fromHtml(getDateString(fixture,holder)));
        holder.weekday.setText(getWeekday(fixture));
        Picasso.get().load(getShield(fixture.homeTeam.id)).placeholder(R.drawable.ic_soccerball).into(holder.hostShield);
        Picasso.get().load(getShield(fixture.awayTeam.id)).placeholder(R.drawable.ic_soccerball).into(holder.awayShield);
    }

    public String getDateString(Fixture fixture, ViewHolder holder) {
        String date = DateUtil.getModifiedDate(Locale.getDefault(), fixture.date.getTime());
        String venueAndDate = fixture.venue.name + " | ";
        if (fixture.state != null && fixture.state.equals("postponed")) {
            venueAndDate += "<font color=#AA2211>" + date + "</font>";
            holder.postponed.setVisibility(VISIBLE);
        } else {
            venueAndDate += date;
        }
        return venueAndDate;
    }

    public String getWeekday(Fixture fixture) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fixture.date);
        return cal.get(Calendar.DATE) + "\n" + context.getResources().getStringArray(R.array.weekdays)[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView hostShield, awayShield;
        private final TextView competitionName, postponed, venueAndDate, hostName, awayName, weekday;

        public ViewHolder(View itemView) {
            super(itemView);

            hostShield = itemView.findViewById(R.id.hostShield);
            awayShield = itemView.findViewById(R.id.awayShield);
            competitionName = itemView.findViewById(R.id.competitionName);
            postponed = itemView.findViewById(R.id.postponed);
            venueAndDate = itemView.findViewById(R.id.venueAndDate);
            hostName = itemView.findViewById(R.id.hostName);
            awayName = itemView.findViewById(R.id.awayName);
            weekday = itemView.findViewById(R.id.weekday);
        }
    }
}