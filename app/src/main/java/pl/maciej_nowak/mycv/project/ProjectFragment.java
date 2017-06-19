package pl.maciej_nowak.mycv.project;

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

public class ProjectFragment extends Fragment {

    public static final String TAG = "AboutFragment";
    private RecyclerView recyclerView;
    private ProjectAdapter adapter;

    public ProjectFragment() {
    }

    public static ProjectFragment newInstance() {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ProjectAdapter(getContext());
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.project_container);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
