package teste.com.fixtures.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompetitionStage {

    @SerializedName("competition")
    @Expose
    public Competition competition;
}
