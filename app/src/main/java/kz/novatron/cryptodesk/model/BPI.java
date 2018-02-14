package kz.novatron.cryptodesk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inetlabs on 2/13/18.
 */

public class BPI {
    @SerializedName("USD")
    private USD usd;
    @SerializedName("KZT")
    private KZT kzt;
    @SerializedName("EUR")
    private EUR eur;
    @SerializedName("RUB")
    private RUB rub;
    @SerializedName("GBP")
    private GBP gbp;

    public KZT getKzt() {
        return kzt;
    }

    public void setKzt(KZT kzt) {
        this.kzt = kzt;
    }

    public EUR getEur() {
        return eur;
    }

    public void setEur(EUR eur) {
        this.eur = eur;
    }

    public RUB getRub() {
        return rub;
    }

    public void setRub(RUB rub) {
        this.rub = rub;
    }

    public USD getUsd() {
        return usd;
    }

    public void setUsd(USD usd) {
        this.usd = usd;
    }

    public GBP getGbp() {
        return gbp;
    }

    public void setGbp(GBP gbp) {
        this.gbp = gbp;
    }
}
