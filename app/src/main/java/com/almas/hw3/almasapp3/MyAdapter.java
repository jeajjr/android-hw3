package com.almas.hw3.almasapp3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
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
    public boolean isEnabled(int position) {
        if (position <= 2)
            return false;
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.movie_list_layout, parent, false);

            holder = new ViewHolder();

            holder.image = (ImageView) rowView.findViewById(R.id.imageViewMoviesImage);
            holder.name = (TextView) rowView.findViewById(R.id.textViewMoviesTitle);
            holder.description = (TextView) rowView.findViewById(R.id.textViewMoviesDescription);
            holder.checkBox = (CheckBox) rowView.findViewById(R.id.checkBoxMovies);
            rowView.setTag(holder);
        }
        else {
            rowView = convertView;
            holder = (ViewHolder) convertView.getTag();
        }

        Map<String, ?> entry = movieList.get(position);
        holder.image.setImageResource((Integer) entry.get("image"));
        holder.name.setText((String) entry.get("name"));
        holder.description.setText((String) entry.get("description"));
        holder.checkBox.setChecked((Boolean) entry.get("selected"));

        if (position%2 == 0)
            rowView.setBackgroundColor(Color.parseColor("#FFCCEE"));
        else
            rowView.setBackgroundColor(Color.parseColor("#BBFFEE"));

        return rowView;
    }
}
