package teste.com.fixtures.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import teste.com.fixtures.Model.Result;
import teste.com.fixtures.R;
import teste.com.fixtures.Util.DateUtil;

import static teste.com.fixtures.Util.DrawableUtil.getShield;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private List<Result> results = new ArrayList<>();
    private Context context;

    public ResultAdapter(Context context, List<Result> results) {
        this.results.addAll(results);
        this.context = context;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setIsRecyclable(false);

        Result result = results.get(position);

        holder.competitionName.setText(result.competitionStage.competition.name);
        holder.hostName.setText(result.homeTeam.name);
        holder.awayName.setText(result.awayTeam.name);
        holder.venueAndDate.setText(getDateString(result));
        holder.hostShield.setImageDrawable(context.getDrawable(getShield(result.homeTeam.id)));
        holder.awayShield.setImageDrawable(context.getDrawable(getShield(result.awayTeam.id)));
        holder.homeScore.setText(String.valueOf(result.score.home));
        holder.awayScore.setText(String.valueOf(result.score.away));

        if (result.score.home > result.score.away)
            holder.homeScore.setTextColor(context.getResources().getColor(R.color.dark_blue));
        if (result.score.away > result.score.home)
            holder.awayScore.setTextColor(context.getResources().getColor(R.color.dark_blue));
    }

    public String getDateString(Result result) {
        String date = DateUtil.getModifiedDate(Locale.getDefault(), result.date.getTime());
        return result.venue.name + " | " + date;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView hostShield, awayShield;
        private final TextView competitionName, venueAndDate, hostName, awayName, homeScore, awayScore;

        public ViewHolder(View itemView) {
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