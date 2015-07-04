package com.example.oussama.smartmemory.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oussama.smartmemory.R;
import com.example.oussama.smartmemory.model.Player;

import java.util.List;

/**
 * Created by Oussama on 04/07/2015.
 */
public class ListViewAdapter extends ArrayAdapter<ListViewItem> {

    public ListViewAdapter(Context context, List<ListViewItem> items) {
        super(context, R.layout.fragment_player_grid, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fragment_player_grid, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.tvUsername = (TextView) convertView.findViewById(R.id.username);
            viewHolder.tvScore = (TextView) convertView.findViewById(R.id.score);
            viewHolder.tvCounter = (TextView) convertView.findViewById(R.id.counter);
            viewHolder.tvTimer = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        ListViewItem item = getItem(position);
        viewHolder.ivIcon.setImageDrawable(item.icon);
        viewHolder.tvUsername.setText(item.username);
        viewHolder.tvScore.setText(String.valueOf(item.score));
        viewHolder.tvCounter.setText(String.valueOf(item.counter));
        viewHolder.tvTimer.setText(String.valueOf(item.time));

        return convertView;
    }

    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */
    private static class ViewHolder {
        ImageView ivIcon;
        TextView tvUsername;
        TextView tvScore;
        TextView tvCounter;
        TextView tvTimer;
    }

}
