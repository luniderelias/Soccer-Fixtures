package teste.com.fixtures.Util;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import teste.com.fixtures.Rest.Api;

public abstract class RestUtil {


    public static Api api = new Retrofit.Builder()
            .baseUrl(Api.URL)
            .client(new OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory
                    .create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Api.class);


}
