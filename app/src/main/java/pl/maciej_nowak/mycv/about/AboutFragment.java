package pl.maciej_nowak.mycv.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.maciej_nowak.mycv.R;

/**
 * Created by Maciej on 19.06.2017.
 */

public class AboutFragment extends Fragment {

    public static final String TAG = "AboutFragment";
    private final String PHONE_NUMBER = "+48533533524";
    private final String EMAIL = "kontakt@maciej-nowak.pl";

    private Button call, message;

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

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAction();
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageAction();
            }
        });

        return view;
    }

    private void callAction() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + PHONE_NUMBER));
        startActivity(intent);
    }

    private void messageAction() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", EMAIL, null));
        startActivity(Intent.createChooser(intent, "Send email..."));
    }
}