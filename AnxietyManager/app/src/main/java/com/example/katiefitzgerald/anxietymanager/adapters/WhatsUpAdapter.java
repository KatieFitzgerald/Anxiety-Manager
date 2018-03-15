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
 * Created by Katie Fitzgerald on 07/03/2018.
 */

public class WhatsUpAdapter extends CursorAdapter {
    public WhatsUpAdapter(Context context, Cursor cursor) {
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


        TextView subjectName = (TextView) view.findViewById(R.id.textView);

        // Extract properties from cursor
        String subject_name = cursor.getString(cursor.getColumnIndexOrThrow("SubjectName"));

        // Populate fields with extracted properties
        subjectName.setText(subject_name);
    }

}
