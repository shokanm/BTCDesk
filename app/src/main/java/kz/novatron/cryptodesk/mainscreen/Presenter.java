package kz.novatron.cryptodesk.mainscreen;

import android.text.Html;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import kz.novatron.cryptodesk.api.ApiClient;
import kz.novatron.cryptodesk.model.GetCurrentPriceResponse;
import kz.novatron.cryptodesk.util.DateUtil;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.content.ContentValues.TAG;
import static kz.novatron.cryptodesk.util.Constants.DOT_JSON;
import static kz.novatron.cryptodesk.util.Constants.EUR;
import static kz.novatron.cryptodesk.util.Constants.GBP;
import static kz.novatron.cryptodesk.util.Constants.KZT;
import static kz.novatron.cryptodesk.util.Constants.RUB;
import static kz.novatron.cryptodesk.util.Constants.USD;

/**
 * Created by inetlabs on 2/13/18.
 */

public class Presenter implements MainScreenContract.Presenter{
    private MainScreenContract.View mView;
    private CompositeSubscription mCompositeSubscription;
    private Locale mLocale;

    Presenter(MainScreenContract.View view, Locale locale){
        mView = view;
        mLocale = locale;
        mCompositeSubscription = new CompositeSubscription();

    }

    @Override
    public void subscribe(String currencyCode) {
        getCurrentPrice(currencyCode);
    }

    private void getCurrentPrice(final String currencyCode) {
        mView.showProgressBar();
        mCompositeSubscription.add(ApiClient.get().getCurrentPrice(currencyCode + DOT_JSON)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCurrentPriceResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgressBar();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(GetCurrentPriceResponse getCurrentPriceResponse) {
                            String currentPriceWithSymbol = "";

                            switch (currencyCode) {
                                case KZT:
                                    currentPriceWithSymbol = getCurrentPriceResponse.getBpi().getKzt().getRateFloat();
                                    break;
                                case USD:
                                    currentPriceWithSymbol = getCurrentPriceResponse.getBpi().getUsd().getRateFloat();
                                    break;
                                case GBP:
                                    currentPriceWithSymbol = getCurrentPriceResponse.getBpi().getGbp().getRateFloat();
                                    break;
                                case EUR:
                                    currentPriceWithSymbol = getCurrentPriceResponse.getBpi().getEur().getRateFloat();
                                    break;
                                case RUB:
                                    currentPriceWithSymbol = getCurrentPriceResponse.getBpi().getRub().getRateFloat();
                                    break;
                            }
                            mView.displayCurrentPrice(currentPriceWithSymbol);
                            mView.displayTimeUpdated(DateUtil.getFormattedDate(getCurrentPriceResponse.getTime().getUpdated(), mLocale));
                            mView.hideProgressBar();
                    }
                }));
    }

    @Override
    public void unsubscribe() {
        mCompositeSubscription.unsubscribe();
    }
}
