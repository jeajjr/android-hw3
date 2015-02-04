package com.almas.hw3.almasapp3;

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            MovieData mv = new MovieData();
            List<Map<String, ?>> movies = mv.getMoviesList();

            ListView moviesList = (ListView) rootView.findViewById(R.id.listViewMovies);
            MyAdapter listAdapter = new MyAdapter(getActivity().getApplicationContext(), movies);

            moviesList.setAdapter(listAdapter);
            moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d(TAG, "onItemClick: " + position);

                    final HashMap<String, ?> itemMap = (HashMap<String, ?>) parent.getItemAtPosition(position);
                    final String item = (String) itemMap.get("name");

                    TextView tv = (TextView) view.findViewById(R.id.textViewMoviesTitle);
                    String name = tv.getText().toString();
                    Toast.makeText(getActivity(), "Selected " + name, Toast.LENGTH_SHORT).show();

                    CheckBox cb = (CheckBox) view.findViewById(R.id.checkBoxMovies);

                    final HashMap<String, Boolean> itemMap_bool = (HashMap<String, Boolean>) itemMap;
                    itemMap_bool.put("selection", !cb.isChecked());
                    cb.setChecked(!cb.isChecked());
                }
            });

            return rootView;
        }
    }
}
