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
    public void displayMap(Coordinates coordinates) {
        this.coordinates = coordinates;
        contentError.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
        MapController mapController = (MapController) mapView.getController();
        mapController.setZoom(18);
        mapController.setCenter(new GeoPoint(coordinates.getLatitude(), coordinates.getLongitude()));
        mapView.setMultiTouchControls(true);
        setMarker(mapView, coordinates);
    }


    @Override
    public void displayDistance(Location location) {

    }

    @Override
    public void displayErrorMap(String error) {
        content.setVisibility(View.GONE);
        contentError.setVisibility(View.VISIBLE);
        errorText.setText(error);
    }

    @Override
    public void displayErrorDistance(String error) {

    }

    @Override
    public void onNetworkState(boolean isEnabled) {
        if(isEnabled) {
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
}
