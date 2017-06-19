package pl.maciej_nowak.mycv.navigate;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.osmdroid.views.MapView;

import pl.maciej_nowak.mycv.R;
import pl.maciej_nowak.mycv.navigate.coordinates.Coordinates;

/**
 * Created by Maciej on 19.06.2017.
 */

public class NavigateFragment extends Fragment implements NavigateView {

    public static final String TAG = "NavigateFragment";

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
                //todo try again to get coordinates
            }
        });
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo go to navigate app
            }
        });

        return view;
    }

    @Override
    public void displayMap(Coordinates coordinates) {

    }

    @Override
    public void displayDistance(Location location) {

    }

    @Override
    public void displayError(String error) {

    }

    @Override
    public void onNetworkState(boolean isEnable) {

    }
}
