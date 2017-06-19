package pl.maciej_nowak.mycv.navigate;

import android.location.Location;

import pl.maciej_nowak.mycv.navigate.coordinates.Coordinates;

/**
 * Created by Maciej on 19.06.2017.
 */

public class NavigatePresenterImpl implements NavigatePresenter, NavigatePresenter.RESULT {
    @Override
    public void getMap() {

    }

    @Override
    public void followDistance() {

    }

    @Override
    public void checkNetworkState() {

    }

    @Override
    public void stopFollowDistance() {

    }

    @Override
    public void navigateToLocation(Coordinates coordinates) {

    }

    @Override
    public void onFetchMapSuccess(Coordinates coordinates) {
        
    }

    @Override
    public void onFetchMapFailed(String error) {

    }

    @Override
    public void onFetchNetworkState(boolean isEnable) {

    }

    @Override
    public void onFetchLocationSuccess(Location location) {

    }

    @Override
    public void onFetchLocationFailed(String error) {

    }
}
