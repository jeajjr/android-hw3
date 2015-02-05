package com.almas.hw3.almasapp3;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.almas.hw3.almaslab3.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewFragment extends Fragment {
    public final String TAG = "PlaceholderFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);

        MovieData mv = new MovieData();
        final List<Map<String, ?>> movies = mv.getMoviesList();

        final ListView moviesList = (ListView) rootView.findViewById(R.id.listViewMovies);

        final ListViewAdapter listAdapter = new ListViewAdapter(getActivity().getApplicationContext(), movies);
        moviesList.setAdapter(listAdapter);

        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick");

                final HashMap<String, ?> itemMap = (HashMap<String, ?>) parent.getItemAtPosition(position);

                TextView tv = (TextView) view.findViewById(R.id.textViewMoviesTitle);
                String name = tv.getText().toString();
                Toast.makeText(getActivity(), "Selected " + name, Toast.LENGTH_SHORT).show();

                CheckBox cb = (CheckBox) view.findViewById(R.id.checkBoxMovies);

                final HashMap<String, Boolean> itemMap_bool = (HashMap<String, Boolean>) itemMap;
                itemMap_bool.put("selected", !cb.isChecked());
                cb.setChecked(!cb.isChecked());
            }
        });

        moviesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                movies.add(position, movies.get(position));
                listAdapter.notifyDataSetChanged();

                return true;
            }
        });

        Button selectAll = (Button) rootView.findViewById(R.id.buttonMoviesSelectAll);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < movies.size(); i++)
                    if (listAdapter.isEnabled(i))
                        ((Map<String, Boolean>) movies.get(i)).put("selected", true);
                listAdapter.notifyDataSetChanged();
            }
        });

        Button clearAll = (Button) rootView.findViewById(R.id.buttonMoviesClearAll);
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Map<String, ?> item : movies)
                    ((Map<String, Boolean>) item).put("selected", false);
                listAdapter.notifyDataSetChanged();
            }
        });

        Button delete = (Button) rootView.findViewById(R.id.buttonMoviesDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deleted = 0;

                for (int i = 0; i < movies.size(); ) {
                    if (((Map<String, Boolean>) movies.get(i)).get("selected")) {
                        movies.remove(i);
                        deleted++;
                    }
                    else
                        i++;
                }
                listAdapter.notifyDataSetChanged();

                Toast.makeText(getActivity(), deleted + " movies were deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
