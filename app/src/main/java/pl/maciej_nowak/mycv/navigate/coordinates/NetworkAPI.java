package pl.maciej_nowak.mycv.navigate.coordinates;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Maciej on 19.06.2017.
 */

public interface NetworkAPI {

    @GET("raw/{id}/")
    Call<Coordinates> getCoordinates(@Path("id") String id);
}
