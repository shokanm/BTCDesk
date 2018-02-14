package kz.novatron.cryptodesk.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by inetlabs on 2/13/18.
 */

public class Time {
    @SerializedName("updated")
    private Date updated;
    @SerializedName("updatedISO")
    private String updatedISO;
    @SerializedName("updateduk")
    private String updateduk;

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUpdatedISO() {
        return updatedISO;
    }

    public void setUpdatedISO(String updatedISO) {
        this.updatedISO = updatedISO;
    }

    public String getUpdateduk() {
        return updateduk;
    }

    public void setUpdateduk(String updateduk) {
        this.updateduk = updateduk;
    }
}
