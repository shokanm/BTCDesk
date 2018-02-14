package kz.novatron.cryptodesk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inetlabs on 2/13/18.
 */

public class GBP {
    @SerializedName("code")
    private String code;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("rate")
    private String rate;
    @SerializedName("description")
    private String description;
    @SerializedName("rate_float")
    private String rateFloat;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRateFloat() {
        return rateFloat;
    }

    public void setRateFloat(String rateFloat) {
        this.rateFloat = rateFloat;
    }
}
