package kz.novatron.cryptodesk.api;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by inetlabs on 2/13/18.
 */

public class ClientConnect {
    private static final int TIME_OUT = 30;

    private CoinDeskEndPointInterface coinDeskEndPointInterface;
    private String apiBaseUrl;
    private OkHttpClient okHttpClient;

    void initRestApi()
    {
        apiBaseUrl = "https://api.coindesk.com/v1/bpi/";

        Gson gson = new GsonBuilder().serializeNulls().create();

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .build();

                return chain.proceed(newRequest);
            }
        };

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .followRedirects(true)
                .addInterceptor(interceptor)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS);


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(loggingInterceptor);


        okHttpClient = httpClientBuilder.build();

        coinDeskEndPointInterface = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(apiBaseUrl)
                .client(okHttpClient)
                .build()
                .create(CoinDeskEndPointInterface.class);
    }


    CoinDeskEndPointInterface getCoinDeskEndPointInterface()
    {
        return coinDeskEndPointInterface;
    }

}
