package teste.com.fixtures.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class Fixture {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("homeTeam")
    @Expose
    public Team homeTeam;
    @SerializedName("awayTeam")
    @Expose
    public Team awayTeam;
    @SerializedName("date")
    @Expose
    public Date date;
    @SerializedName("competitionStage")
    @Expose
    public CompetitionStage competitionStage;
    @SerializedName("venue")
    @Expose
    public Venue venue;
    @SerializedName("state")
    @Expose
    public String state;

}