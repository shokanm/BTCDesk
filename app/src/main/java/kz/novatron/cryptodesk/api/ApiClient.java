package kz.novatron.cryptodesk.api;

import android.util.Log;

import kz.novatron.cryptodesk.model.GetCurrentPriceResponse;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by inetlabs on 2/13/18.
 */

public class ApiClient {

    private CoinDeskEndPointInterface coinDeskEndPointInterface;
    private ClientConnect clientConnect;

    private static ApiClient instance;

    public static ApiClient get() {
        if(instance == null){
            instance = new ApiClient();
        }
        return instance;
    }

    public ApiClient() {
        clientConnect = new ClientConnect();

        clientConnect.initRestApi();

        coinDeskEndPointInterface = clientConnect.getCoinDeskEndPointInterface();
    }

    public Observable<GetCurrentPriceResponse> getCurrentPrice(final String currencyCode) {

        Observable<GetCurrentPriceResponse> o = Observable.create(new Observable.OnSubscribe<GetCurrentPriceResponse>() {

            @Override
            public void call(final Subscriber<? super GetCurrentPriceResponse> subscriber) {
                coinDeskEndPointInterface.getCurrentPrice(currencyCode).subscribe(new Subscriber<GetCurrentPriceResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError((Throwable) e);
                    }

                    @Override
                    public void onNext(GetCurrentPriceResponse getCurrentPriceResponse) {
                        subscriber.onNext(getCurrentPriceResponse);
                    }
                });
            }
        });
        return o;
    }
}
