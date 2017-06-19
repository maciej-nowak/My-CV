package pl.maciej_nowak.mycv.navigate;

import pl.maciej_nowak.mycv.navigate.coordinates.Coordinates;

/**
 * Created by Maciej on 19.06.2017.
 */

public interface NavigateInteractor {

    void fetchMap();
    void fetchNetworkState();
    void fetchLocation();
    void stopLocation();
    void launchNavigation(Coordinates coordinates);
}
