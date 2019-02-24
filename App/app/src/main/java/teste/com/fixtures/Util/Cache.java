package teste.com.fixtures.Util;

import java.util.ArrayList;
import java.util.List;

import teste.com.fixtures.Model.Fixture;
import teste.com.fixtures.Model.Result;

public class Cache {

    private static List<Fixture> fixtures = new ArrayList<>();
    private static List<Result> results = new ArrayList<>();

    public static List<Fixture> getFixtures() {
        return fixtures;
    }

    public static void setFixtures(List<Fixture> fixtures) {
        Cache.fixtures.clear();
        Cache.fixtures.addAll(fixtures);
    }

    public static List<Result> getResults() {
        return results;
    }

    public static void setResults(List<Result> results) {
        Cache.results.clear();
        Cache.results.addAll(results);
    }

}
