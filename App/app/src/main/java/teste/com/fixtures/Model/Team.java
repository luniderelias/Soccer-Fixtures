package teste.com.fixtures.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("shortName")
    @Expose
    public String shortName;
    @SerializedName("abbr")
    @Expose
    public String abbr;
    @SerializedName("alias")
    @Expose
    public String alias;
}