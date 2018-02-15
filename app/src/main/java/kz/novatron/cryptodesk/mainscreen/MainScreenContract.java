package kz.novatron.cryptodesk.mainscreen;

/**
 * Created by inetlabs on 2/13/18.
 */

public interface MainScreenContract {
    interface View{

        void showProgressBar();
        void hideProgressBar();
        void showError(String errorMessage);
        void displayCurrentPrice(String currentPrice);
        void displayTimeUpdated(String timeUpdated);
    }

    interface Presenter{
        void unsubscribe();
        void subscribe(String currencyCode);
    }
}
