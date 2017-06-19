package pl.maciej_nowak.mycv.navigate;

import android.content.Context;
import android.location.Location;

import pl.maciej_nowak.mycv.navigate.coordinates.Coordinates;

/**
 * Created by Maciej on 19.06.2017.
 */

public class NavigatePresenterImpl implements NavigatePresenter, NavigatePresenter.RESULT {

    private NavigateInteractor interactor;
    private NavigateView view;

    public NavigatePresenterImpl(Context context, NavigateView view) {
        this.view = view;
        interactor = new NavigateInteractorImpl(context, this);
    }

    @Override
    public void getMap() {
        interactor.fetchMap();
    }

    @Override
    public void followDistance() {
        interactor.fetchLocation();
    }

    @Override
    public void checkNetworkState() {
        interactor.fetchNetworkState();
    }

    @Override
    public void stopFollowDistance() {
        interactor.stopLocation();
    }

    @Override
    public void navigateToLocation(Coordinates coordinates) {
        interactor.launchNavigation(coordinates);
    }

    @Override
    public void onFetchMapSuccess(Coordinates coordinates) {
        view.displayMap(coordinates);
    }

    @Override
    public void onFetchMapFailed(String error) {
        view.displayErrorMap(error);
    }

    @Override
    public void onFetchNetworkState(boolean isEnabled) {
        view.onNetworkState(isEnabled);
    }

    @Override
    public void onFetchLocationSuccess(Location location) {
        view.displayDistance(location);
    }

    @Override
    public void onFetchLocationFailed(String error) {
        view.displayErrorDistance(error);
    }
}
