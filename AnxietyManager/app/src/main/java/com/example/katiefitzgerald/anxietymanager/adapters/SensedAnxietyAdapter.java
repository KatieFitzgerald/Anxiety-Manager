package com.example.katiefitzgerald.anxietymanager.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.activities.SensedActivity;

import java.util.ArrayList;

/**
 * Created by Katie Fitzgerald on 18/03/2018.
 */


public class SensedAnxietyAdapter extends BaseAdapter {

    private ArrayList<String> time;
    private ArrayList<String> location;

    public LayoutInflater inflater;

    public SensedAnxietyAdapter(Context context, ArrayList<String> time , ArrayList<String> location){

        super();

        this.time = time;
        this.location = location;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if(row == null){

            row = inflater.inflate(R.layout.list_view_sensed_anxiety, parent, false);
        }

        TextView timeItem = row.findViewById(R.id.timeItem);
        timeItem.setText(time.get(position));

        return row;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

} // end custom adapter