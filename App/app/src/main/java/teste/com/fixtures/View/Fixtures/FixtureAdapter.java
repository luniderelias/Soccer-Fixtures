package teste.com.fixtures.View.Fixtures;

import teste.com.fixtures.Model.Fixture;
import teste.com.fixtures.Presenter.FixtureAdapter.FixtureAdapterPresenter;
import teste.com.fixtures.Presenter.FixtureAdapter.IFixtureAdapterPresenter;
import teste.com.fixtures.R;
import teste.com.fixtures.Util.Cache;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static teste.com.fixtures.Util.DrawableUtil.getShield;

public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.ViewHolder> implements IFixtureAdapterView {

    private Context context;
    IFixtureAdapterPresenter fixtureAdapterPresenter;
    private Fixture fixture;
    private ViewHolder viewHolder;

    public FixtureAdapter(Context context) {
        fixtureAdapterPresenter = new FixtureAdapterPresenter();
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixture_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return Cache.getFixtures().size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        viewHolder = holder;
        viewHolder.setIsRecyclable(false);
        fixture = Cache.getFixtures().get(position);

        setHolderTexts();
        setShields();
    }

    @Override
    public void setHolderTexts(){
        viewHolder.competitionName.setText(fixture.competitionStage.competition.name);
        viewHolder.hostName.setText(fixture.homeTeam.name);
        viewHolder.awayName.setText(fixture.awayTeam.name);
        viewHolder.venueAndDate.setText(Html.fromHtml(fixtureAdapterPresenter.getFixtureDateString(fixture)));
        viewHolder.weekday.setText(fixtureAdapterPresenter.getWeekday(context, fixture));

        if (fixture.state != null && fixture.state.equals("postponed"))
            viewHolder.postponed.setVisibility(View.VISIBLE);
    }

    @Override
    public void setShields(){
        Glide.with(context).load(getShield(fixture.homeTeam.id)).into(viewHolder.hostShield);
        Glide.with(context).load(getShield(fixture.awayTeam.id)).into(viewHolder.awayShield);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView hostShield, awayShield;
        public final TextView competitionName, postponed, venueAndDate, hostName, awayName, weekday;

        private ViewHolder(View itemView) {
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