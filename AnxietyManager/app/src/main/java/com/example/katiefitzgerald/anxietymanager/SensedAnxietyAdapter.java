package com.example.katiefitzgerald.anxietymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Katie Fitzgerald on 20/11/2017.
 */

public class SensedAnxietyAdapter  extends ArrayAdapter {

    ArrayList<AnxietyEpisode> anxietyEpisode;
    public LayoutInflater inflater;

    public SensedAnxietyAdapter(Context context, int textViewResourceId, ArrayList<AnxietyEpisode> episode) {

        super(context, textViewResourceId, episode);
        anxietyEpisode = episode;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        //return super.getView(position, convertView, parent);

        View row = convertView;

        if(row == null){

            row = inflater.inflate(R.layout.list_view_items, parent, false);
        }

        AnxietyEpisode item = anxietyEpisode.get(position);

        TextView textView =(TextView)row.findViewById(R.id.textView);
        textView.setText(item.toString());
        TextView locationItem =(TextView)row.findViewById(R.id.locationItem);
        locationItem.setText(" location");

        return row;
    }
} // end custom adapter