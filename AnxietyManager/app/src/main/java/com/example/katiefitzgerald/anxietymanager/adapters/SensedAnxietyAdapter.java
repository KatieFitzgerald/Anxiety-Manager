package com.example.katiefitzgerald.anxietymanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.katiefitzgerald.anxietymanager.model.AnxietyEpisode;
import com.example.katiefitzgerald.anxietymanager.R;

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

        View row = convertView;

        if(row == null){

            row = inflater.inflate(R.layout.list_view_sensed_anxiety, parent, false);
        }

        AnxietyEpisode item = anxietyEpisode.get(position);

        TextView textView = row.findViewById(R.id.textView);
        textView.setText(item.toString());
        TextView locationItem =row.findViewById(R.id.locationItem);
        locationItem.setText(item.getLocation());

        return row;
    }
} // end custom adapter