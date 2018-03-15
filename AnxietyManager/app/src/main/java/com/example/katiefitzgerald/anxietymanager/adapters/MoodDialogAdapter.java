package com.example.katiefitzgerald.anxietymanager.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.katiefitzgerald.anxietymanager.R;

/**
 * Created by Katie Fitzgerald on 10/03/2018.
 */

public class MoodDialogAdapter extends CursorAdapter {
    public MoodDialogAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    //inflate to a new view
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_view_questionnaire, parent, false);
    }

    //bind data to the given view
    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        TextView moodName =  view.findViewById(R.id.textView);

        // Extract properties from cursor
        String mood_name = cursor.getString(cursor.getColumnIndexOrThrow("MoodName"));

        // Populate fields with extracted properties
        moodName.setText(mood_name);
    }

}