package com.example.katiefitzgerald.anxietymanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Katie Fitzgerald on 20/11/2017.
 */

public class SensedAnxietyAdapter  extends ArrayAdapter {

    ArrayList<AnxietyEpisode> anxietyEpisode;

    public SensedAnxietyAdapter(Context context, ArrayList<AnxietyEpisode> episode) {

        super(context, 0, episode);
        anxietyEpisode = episode;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            //convertView = mInflater.inflate(R.layout.list_view_items,parent,false);

            // inflate custom layout called row
            holder = new ViewHolder();
            holder.tv =(TextView) convertView.findViewById(R.id.textView);

            // initialize textview
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        AnxietyEpisode item = (AnxietyEpisode)anxietyEpisode.get(position);
        holder.tv.setText(item.toString());
        // set the name to the text;

        return convertView;

    }

    static class ViewHolder
    {

        TextView tv;
    }

}
