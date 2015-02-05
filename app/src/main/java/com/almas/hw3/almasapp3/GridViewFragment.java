package com.almas.hw3.almasapp3;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.GridView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.almas.hw3.almaslab3.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GridViewFragment extends Fragment {
    private final String TAG = "GridViewFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inputFragmentView = inflater.inflate(R.layout.fragment_grid_view, container, false);

        MovieData mv = new MovieData();
        final List<Map<String, ?>> movies = mv.getMoviesList();

        // prepared arrayList and passed it to the Adapter class
        GridViewAdapter mAdapter = new GridViewAdapter(getActivity().getApplicationContext(), movies);

        // Set custom adapter to gridView
        GridView gridView = (GridView) inputFragmentView.findViewById(R.id.GridViewMovies);
        gridView.setAdapter(mAdapter);

        // Implement On Item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getActivity(), "Selected movie " + movies.get(position).get("name"), Toast.LENGTH_SHORT).show();
            }
        });

        return inputFragmentView;
    }
}
