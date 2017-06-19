package pl.maciej_nowak.mycv.experience;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.maciej_nowak.mycv.R;

/**
 * Created by Maciej on 19.06.2017.
 */

public class ExperienceFragment extends Fragment {

    public static final String TAG = "EexperienceFragment";

    private RecyclerView recyclerView;
    private ExperienceAdapter adapter;

    public ExperienceFragment() {
    }

    public static ExperienceFragment newInstance() {
        ExperienceFragment fragment = new ExperienceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ExperienceAdapter(getContext());
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.experience_container);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
