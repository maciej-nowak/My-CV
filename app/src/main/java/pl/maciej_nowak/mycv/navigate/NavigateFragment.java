package pl.maciej_nowak.mycv.navigate;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import pl.maciej_nowak.mycv.R;
import pl.maciej_nowak.mycv.navigate.coordinates.Coordinates;

/**
 * Created by Maciej on 19.06.2017.
 */

public class NavigateFragment extends Fragment implements NavigateView {

    public static final String TAG = "NavigateFragment";

    private Coordinates coordinates;

    private NavigatePresenter presenter;

    private View content, contentError;
    private MapView mapView;
    private TextView distanceDescText, distanceText, errorText;
    private Button tryAgain, navigate;

    public NavigateFragment() {
    }

    public static NavigateFragment newInstance() {
        NavigateFragment fragment = new NavigateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NavigatePresenterImpl(getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigate, container, false);
        content = view.findViewById(R.id.navigate_content);
        contentError = view.findViewById(R.id.navigate_content_error);
        mapView = (MapView) view.findViewById(R.id.navigate_map);
        distanceDescText = (TextView) view.findViewById(R.id.navigate_distance_description);
        distanceText = (TextView) view.findViewById(R.id.navigate_distance);
        errorText = (TextView) view.findViewById(R.id.navigate_error_text);
        tryAgain = (Button) view.findViewById(R.id.navigate_try_again);
        navigate = (Button) view.findViewById(R.id.navigate_app);

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkNetworkState();
            }
        });
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.navigateToLocation(coordinates);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.checkNetworkState();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stopFollowDistance();
    }

    @Override
    public void displayMap(Coordinates coordinates) {
        this.coordinates = coordinates;
        contentError.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
        MapController mapController = (MapController) mapView.getController();
        mapController.setZoom(18);
        mapController.setCenter(new GeoPoint(coordinates.getLatitude(), coordinates.getLongitude()));
        mapView.setMultiTouchControls(true);
        setMarker(mapView, coordinates);
        presenter.followDistance();
    }

    @Override
    public void displayDistance(Location location) {
        if(location != null) {
            float[] result = new float[1];
            Location.distanceBetween(coordinates.getLatitude(), coordinates.getLongitude(), location.getLatitude(), location.getLongitude(), result);
            distanceText.setText(round(result[0], 2));
        }
    }

    @Override
    public void displayErrorMap(String error) {
        content.setVisibility(View.GONE);
        contentError.setVisibility(View.VISIBLE);
        errorText.setText(error);
    }

    @Override
    public void displayErrorDistance(String error) {
        distanceText.setText(error);
    }

    @Override
    public void onNetworkState(boolean isEnable) {
        if(isEnable) {
            presenter.getMap();
        }
        else {
            content.setVisibility(View.GONE);
            contentError.setVisibility(View.VISIBLE);
        }
    }

    private void setMarker(MapView mapView, Coordinates coordinates) {
        ArrayList<OverlayItem> overlayItems = new ArrayList<>();
        ItemizedIconOverlay<OverlayItem> itemizedOverlayItems
                = new ItemizedIconOverlay<>(getContext(), overlayItems, null);
        itemizedOverlayItems.addItem(new OverlayItem(coordinates.getName(), coordinates.getName(),
                new GeoPoint(coordinates.getLatitude(), coordinates.getLongitude())));
        mapView.getOverlays().add(itemizedOverlayItems);
    }

    private String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        if(value < 1000) {
            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(0, RoundingMode.HALF_UP);
            return bd.floatValue() + " m";
        }
        else {
            BigDecimal bd = new BigDecimal(value / 1000);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue() + " km";
        }
    }
}
