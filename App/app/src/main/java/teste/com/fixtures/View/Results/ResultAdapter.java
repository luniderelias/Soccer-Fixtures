package teste.com.fixtures.View.Results;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import teste.com.fixtures.Model.Result;
import teste.com.fixtures.Presenter.FixtureAdapter.FixtureAdapterPresenter;
import teste.com.fixtures.Presenter.FixtureAdapter.IFixtureAdapterPresenter;
import teste.com.fixtures.R;
import teste.com.fixtures.Util.Cache;
import teste.com.fixtures.Util.DateUtil;

import static teste.com.fixtures.Util.DrawableUtil.getShield;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> implements IResultAdapterView{

    private Context context;
    private IFixtureAdapterPresenter fixtureAdapterPresenter;
    private Result result;
    private ViewHolder viewHolder;

    public ResultAdapter(Context context) {
        fixtureAdapterPresenter = new FixtureAdapterPresenter();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return Cache.getResults().size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        viewHolder = holder;
        viewHolder.setIsRecyclable(false);
        result = Cache.getResults().get(position);

        setResultsTexts();
        setWinnerText();
        setShields();
    }

    @Override
    public void setResultsTexts(){
        viewHolder.competitionName.setText(result.competitionStage.competition.name);
        viewHolder.hostName.setText(result.homeTeam.name);
        viewHolder.awayName.setText(result.awayTeam.name);
        viewHolder.venueAndDate.setText(fixtureAdapterPresenter.getResultDateString(result));
        viewHolder.homeScore.setText(String.valueOf(result.score.home));
        viewHolder.awayScore.setText(String.valueOf(result.score.away));
    }

    @Override
    public void setWinnerText(){
        if (result.score.home > result.score.away)
            viewHolder.homeScore.setTextColor(context.getResources().getColor(R.color.dark_blue));
        if (result.score.away > result.score.home)
            viewHolder.awayScore.setTextColor(context.getResources().getColor(R.color.dark_blue));
    }

    @Override
    public void setShields(){
        Glide.with(context).load(getShield(result.homeTeam.id)).into(viewHolder.hostShield);
        Glide.with(context).load(getShield(result.awayTeam.id)).into(viewHolder.awayShield);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView hostShield, awayShield;
        private final TextView competitionName, venueAndDate, hostName, awayName, homeScore, awayScore;

        private ViewHolder(View itemView) {
            super(itemView);

            hostShield = itemView.findViewById(R.id.hostShield);
            awayShield = itemView.findViewById(R.id.awayShield);
            competitionName = itemView.findViewById(R.id.competitionName);
            venueAndDate = itemView.findViewById(R.id.venueAndDate);
            hostName = itemView.findViewById(R.id.hostName);
            awayName = itemView.findViewById(R.id.awayName);
            homeScore = itemView.findViewById(R.id.homeScore);
            awayScore = itemView.findViewById(R.id.awayScore);
        }
    }
}