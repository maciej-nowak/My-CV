package pl.maciej_nowak.mycv.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import pl.maciej_nowak.mycv.R;
import pl.maciej_nowak.mycv.navigate.NavigateActivity;

/**
 * Created by Maciej on 19.06.2017.
 */

public class AboutFragment extends Fragment {

    public static final String TAG = "AboutFragment";
    private final String PHONE_NUMBER = "+48533533524";
    private final String EMAIL = "kontakt@maciej-nowak.pl";

    private Button call, message, navigate;

    public AboutFragment() {
    }

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        call = (Button) view.findViewById(R.id.call_me);
        message = (Button) view.findViewById(R.id.message_me);
        navigate = (Button) view.findViewById(R.id.navigate);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionAction(PermissionProvider.REQUEST_CALL_CODE);
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageAction();
            }
        });
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionAction(PermissionProvider.REQUEST_LOCATION_CODE);
            }
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PermissionProvider.REQUEST_CALL_CODE) {
            if (PermissionProvider.arePermissionsProvided(grantResults))
                callAction();
            else
                Toast.makeText(getContext(), R.string.accept_permissions, Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == PermissionProvider.REQUEST_LOCATION_CODE) {
            if(PermissionProvider.arePermissionsProvided(grantResults))
                navigateAction();
            else
                Toast.makeText(getContext(), R.string.accept_permissions, Toast.LENGTH_SHORT).show();
        }
    }

    private void permissionAction(int permissionCode) {
        if(PermissionProvider.arePermissionsGranted(getActivity(), permissionCode)) {
            if(permissionCode == PermissionProvider.REQUEST_CALL_CODE)
                callAction();
            else if(permissionCode == PermissionProvider.REQUEST_LOCATION_CODE)
                navigateAction();
        }
        else if(PermissionProvider.isRequiredVersion()) {
            requestPermissions(PermissionProvider.requiredPermissions(getActivity(), permissionCode), permissionCode);
        }
    }

    private void callAction() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + PHONE_NUMBER));
        startActivity(intent);
    }

    private void messageAction() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", EMAIL, null));
        startActivity(Intent.createChooser(intent, getContext().getString(R.string.send_mail)));
    }

    private void navigateAction() {
        Intent intent = new Intent(getContext(), NavigateActivity.class);
        startActivity(intent);
    }
}
