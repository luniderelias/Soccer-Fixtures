package teste.com.fixtures.Rest;



import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import teste.com.fixtures.Model.Fixture;
import teste.com.fixtures.Model.Result;

public interface Api {

    String URL = "https://storage.googleapis.com/cdn-og-test-api/test-task/";

    @GET("fixtures.json")
    Observable<List<Fixture>> getFixtures();

    @GET("results.json")
    Observable<List<Result>> getResults();

}
