package com.almas.hw3.almasapp3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.almas.hw3.almaslab3.R;

import java.util.List;
import java.util.Map;

/**
 * Created by jeajjr on 2/5/15.
 */
public class GridViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<Map<String,?>> movieList;

    public GridViewAdapter(Context context, List<Map<String,?>> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Map<String,?> getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class GridViewHolder
    {
        public ImageView image;
        public TextView title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GridViewHolder view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
        {
            view = new GridViewHolder();
            convertView = inflater.inflate(R.layout.movie_list_grid_layout, null);

            view.title = (TextView) convertView.findViewById(R.id.textViewGrid);
            view.image = (ImageView) convertView.findViewById(R.id.imageViewGrid);

            convertView.setTag(view);
        }
        else
        {
            view = (GridViewHolder) convertView.getTag();
        }

        Map<String, ?> entry = movieList.get(position);

        view.title.setText((String) entry.get("name"));
        view.image.setImageResource((Integer) entry.get("image"));

        return convertView;
    }
}
