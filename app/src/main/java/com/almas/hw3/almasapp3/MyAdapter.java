package com.almas.hw3.almasapp3;

import android.content.Context;
import android.graphics.Color;
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
 * Created by jeajjr on 2/3/15.
 */
public class MyAdapter extends BaseAdapter {
    private final Context context;
    private final List<Map<String,?>> movieList;

   public MyAdapter (Context context, List<Map<String,?>> movieList) {
       this.context = context;
       this.movieList = movieList;
   }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.movie_list_layout, parent, false);

        ImageView image = (ImageView) rowView.findViewById(R.id.imageViewMoviesImage);
        TextView title = (TextView) rowView.findViewById(R.id.textViewMoviesTitle);
        TextView description = (TextView) rowView.findViewById(R.id.textViewMoviesDescription);

        Map<String, ?> entry = movieList.get(position);
        image.setImageResource((Integer) entry.get("image"));
        title.setText((String) entry.get("name"));
        description.setText((String) entry.get("description"));

        if (position%2 == 0)
            rowView.setBackgroundColor(0x0000FF00);
        else
            rowView.setBackgroundColor(0x00FFFF00);

        rowView.invalidate();

        return rowView;
    }
}
