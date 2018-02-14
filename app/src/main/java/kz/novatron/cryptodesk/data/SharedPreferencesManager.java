package kz.novatron.cryptodesk.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Date;

import static kz.novatron.cryptodesk.util.Constants.KZT;
import static kz.novatron.cryptodesk.util.Constants.SELECTED_CURRENCY;
import static kz.novatron.cryptodesk.util.Constants.SHARED_PREF_FILE_NAME;

/**
 * Created by inetlabs on 2/13/18.
 */


public class SharedPreferencesManager {

    private SharedPreferences sharedpreferences;

    public SharedPreferencesManager(Context context) {
        this.sharedpreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
    }


    public void setSelectedCurrency(CharSequence currency) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SELECTED_CURRENCY, currency.toString());
        editor.apply();
    }

    public String getSelectedCurrency(){
        return sharedpreferences.getString(SELECTED_CURRENCY, KZT);
    }
}
