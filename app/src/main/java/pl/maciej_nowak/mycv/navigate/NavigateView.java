package pl.maciej_nowak.mycv.navigate;

import android.location.Location;

import pl.maciej_nowak.mycv.navigate.coordinates.Coordinates;

/**
 * Created by Maciej on 19.06.2017.
 */

public interface NavigateView {

    void displayMap(Coordinates coordinates);
    void displayDistance(Location location);
    void displayErrorMap(String error);
    void displayErrorDistance(String error);
    void onNetworkState(boolean isEnable);
}
