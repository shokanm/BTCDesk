package kz.novatron.cryptodesk.api;

import kz.novatron.cryptodesk.model.GetCurrentPriceResponse;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by inetlabs on 2/13/18.
 */

public interface CoinDeskEndPointInterface {
    @GET("currentprice/{currencyCode}")
    Observable<GetCurrentPriceResponse> getCurrentPrice(@Path("currencyCode") String currencyCode);
}
