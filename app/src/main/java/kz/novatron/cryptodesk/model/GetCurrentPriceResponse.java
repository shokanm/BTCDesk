package kz.novatron.cryptodesk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inetlabs on 2/13/18.
 */

public class GetCurrentPriceResponse {
    @SerializedName("time")
    private Time time;
    @SerializedName("disclaimer")
    private String disclaimer;
    @SerializedName("chartName")
    private String chartName;
    @SerializedName("bpi")
    private BPI bpi;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public BPI getBpi() {
        return bpi;
    }

    public void setBpi(BPI bpi) {
        this.bpi = bpi;
    }
}
