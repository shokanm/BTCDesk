package kz.novatron.cryptodesk.mainscreen;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.novatron.cryptodesk.R;
import kz.novatron.cryptodesk.data.SharedPreferencesManager;

import static kz.novatron.cryptodesk.util.Constants.EUR;
import static kz.novatron.cryptodesk.util.Constants.GBP;
import static kz.novatron.cryptodesk.util.Constants.KZT;
import static kz.novatron.cryptodesk.util.Constants.NETWORK_ERROR_PATTERN;
import static kz.novatron.cryptodesk.util.Constants.RUB;
import static kz.novatron.cryptodesk.util.Constants.USD;

public class CurrencyListActivity extends AppCompatActivity implements MainScreenContract.View,
        SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.text_chartName)
    TextView textChartName;

    @BindView(R.id.text_current_price)
    TextView textCurrentPrice;

    @BindView(R.id.text_time_updated)
    TextView textTimeUpdated;

    @BindView(R.id.text_disclaimer)
    TextView textDisclaimer;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    @BindView(R.id.spinner_currencies)
    AppCompatSpinner spinner;

    private MainScreenContract.Presenter mPresenter;
    private String[] currencies;
    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);
        ButterKnife.bind(this);
        Locale locale = Resources.getSystem().getConfiguration().locale;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies_array, R.layout.spinner_style);
        adapter.setDropDownViewResource(R.layout.spinner_style);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        currencies = getResources().getStringArray(R.array.currencies_array);

        sharedPreferencesManager = new SharedPreferencesManager(this);

        mPresenter = new Presenter(this, locale);

        swipeLayout.setOnRefreshListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        spinner.setSelection(getPositionByName(sharedPreferencesManager.getSelectedCurrency()));
        mPresenter.subscribe(sharedPreferencesManager.getSelectedCurrency());
    }

    private int getPositionByName(String selectedCurrency) {
        switch(selectedCurrency){
            case KZT: return 0;
            case USD: return 1;
            case GBP: return 2;
            case EUR: return 3;
            case RUB: return 4;
            default: return 0;
        }
    }

    @Override
    public void displayCurrentPrice(String currentPrice) {
        textCurrentPrice.setText(currentPrice);
    }

    @Override
    public void displayTimeUpdated(String timeUpdated) {
        textTimeUpdated.setText(timeUpdated);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, getErrorMessage(errorMessage), Toast.LENGTH_SHORT).show();
    }

    private String getErrorMessage(String errorMessage){
        String mess = (errorMessage != null && !errorMessage.equals("")) ? errorMessage : getString(R.string.error_message);
        if(mess.contains(NETWORK_ERROR_PATTERN))
            return getString(R.string.error_connection);
        else
            return mess;
    }

    @Override
    public void onRefresh() {
        mPresenter.subscribe(sharedPreferencesManager.getSelectedCurrency());
    }



    @OnClick(R.id.text_disclaimer)
    public void sponsorNameClicked(){
        Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.sponsor_page)));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sharedPreferencesManager.setSelectedCurrency(currencies[position]);
        mPresenter.subscribe(sharedPreferencesManager.getSelectedCurrency());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
