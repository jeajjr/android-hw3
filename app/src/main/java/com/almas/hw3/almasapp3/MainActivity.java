package com.almas.hw3.almasapp3;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
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


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
/*
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_list_view:
                getFragmentManager().beginTransaction()
                        .remove(GridViewFragment())
                        .commit();
                return true;
            case R.id.action_grid_view:
                getFragmentManager().beginTransaction()
                        .add(R.id.container, new GridViewFragment())
                        .commit();
                return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public final String TAG = "PlaceholderFragment";
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView");
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            MovieData mv = new MovieData();
            final List<Map<String, ?>> movies = mv.getMoviesList();

            final ListView moviesList = (ListView) rootView.findViewById(R.id.listViewMovies);

            final MyAdapter listAdapter = new MyAdapter(getActivity().getApplicationContext(), movies);
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
}
