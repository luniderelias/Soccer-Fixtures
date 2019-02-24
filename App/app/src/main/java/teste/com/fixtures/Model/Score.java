package teste.com.fixtures.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Score {

    @SerializedName("home")
    @Expose
    public int home;
    @SerializedName("away")
    @Expose
    public int away;
    @SerializedName("winner")
    @Expose
    public String winner;
}