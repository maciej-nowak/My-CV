package pl.maciej_nowak.mycv.navigate;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.otto.Subscribe;

import pl.maciej_nowak.mycv.R;
import pl.maciej_nowak.mycv.navigate.coordinates.Coordinates;
import pl.maciej_nowak.mycv.navigate.coordinates.NetworkAPI;
import pl.maciej_nowak.mycv.navigate.location.BusProvider;
import pl.maciej_nowak.mycv.navigate.location.GPSService;
import pl.maciej_nowak.mycv.navigate.location.NewLocationEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maciej on 19.06.2017.
 */

public class NavigateInteractorImpl implements NavigateInteractor {

    private final String NETWORK_URL = "http://pastebin.com/";
    private final String COORDINATES_ID = "uYCM5u0P";
    private final int RESPONSE_CODE_OK = 200;

    private boolean isServiceBound = false;

    private Context context;
    private NetworkAPI network;
    private NavigatePresenter.RESULT result;

    public NavigateInteractorImpl(Context context, NavigatePresenter.RESULT result) {
        this.context = context;
        this.result = result;
        setupRetrofit();
        setCallback();
    }

    @Override
    public void fetchMap() {
        Call<Coordinates> call = network.getCoordinates(COORDINATES_ID);
        call.enqueue(setCallback());
    }

    @Override
    public void fetchNetworkState() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean state = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        result.onFetchNetworkState(state);
    }

    @Override
    public void fetchLocation() {
        BusProvider.getInstance().register(this);
        bindGPSService();
        isServiceBound = true;
    }

    @Override
    public void stopLocation() {
        if(isServiceBound) {
            BusProvider.getInstance().unregister(this);
            unbindGPSService();
        }
    }

    @Override
    public void launchNavigation(Coordinates coordinates) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + coordinates.getLatitude() + "," + coordinates.getLongitude() +
                        " (" + coordinates.getName() + ")"));
        context.startActivity(intent);
    }

    private void setupRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NETWORK_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        network = retrofit.create(NetworkAPI.class);
    }

    private Callback setCallback() {
        return new Callback<Coordinates>() {
            @Override
            public void onResponse(@NonNull Call<Coordinates> call, @NonNull Response<Coordinates> response) {
                int statusCode = response.code();
                if(statusCode == RESPONSE_CODE_OK) {
                    Coordinates coordinates = response.body();
                    result.onFetchMapSuccess(coordinates);
                }
                else {
                    result.onFetchMapFailed(context.getString(R.string.navigate_no_data));
                }
            }
            @Override
            public void onFailure(@NonNull Call<Coordinates> call, @NonNull Throwable t) {
                result.onFetchMapFailed(t.toString());
            }
        };
    }

    private void bindGPSService() {
        Intent intent = new Intent(context, GPSService.class);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        isServiceBound = true;
    }

    private void unbindGPSService() {
        context.unbindService(serviceConnection);
        isServiceBound = false;
    }

    @Subscribe
    public void getNewLocation(NewLocationEvent location) {
        result.onFetchLocationSuccess(location.getLocation());
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isServiceBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBound = false;
        }
    };
}
