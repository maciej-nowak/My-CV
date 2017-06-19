package pl.maciej_nowak.mycv.navigate;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pl.maciej_nowak.mycv.R;
import pl.maciej_nowak.mycv.navigate.coordinates.Coordinates;
import pl.maciej_nowak.mycv.navigate.coordinates.NetworkAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maciej on 19.06.2017.
 */

public class NavigateInteractorImpl implements NavigateInteractor {

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
        Call<Coordinates> call = network.getCoordinates("uYCM5u0P");
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

    }

    @Override
    public void stopLocation() {

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
                .baseUrl("http://pastebin.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        network = retrofit.create(NetworkAPI.class);
    }

    private Callback setCallback() {
        return new Callback<Coordinates>() {
            @Override
            public void onResponse(@NonNull Call<Coordinates> call, @NonNull Response<Coordinates> response) {
                int statusCode = response.code();
                if(statusCode == 200) {
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
}
