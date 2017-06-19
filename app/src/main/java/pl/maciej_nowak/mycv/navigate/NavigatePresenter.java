package pl.maciej_nowak.mycv.navigate;

import android.location.Location;

import pl.maciej_nowak.mycv.navigate.coordinates.Coordinates;

/**
 * Created by Maciej on 19.06.2017.
 */

public interface NavigatePresenter {

    void getMap();
    void followDistance();
    void checkNetworkState();
    void stopFollowDistance();
    void navigateToLocation(Coordinates coordinates);

    interface RESULT {
        void onFetchMapSuccess(Coordinates coordinates);
        void onFetchMapFailed(String error);
        void onFetchNetworkState(boolean isEnable);
        void onFetchLocationSuccess(Location location);
        void onFetchLocationFailed(String error);
    }
}
