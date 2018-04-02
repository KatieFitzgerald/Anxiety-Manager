package com.example.katiefitzgerald.anxietymanager.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.activities.HomeActivity;
import com.github.mikephil.charting.charts.PieChart;

/**
 * Created by Katie Fitzgerald on 28/03/2018.
 */

public class SubjectFragment extends Fragment {

    private PieChart subjectChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.subject_fragment, container, false);

        subjectChart = rootView.findViewById(R.id.subjectChart);
        HomeActivity.addDataSet(subjectChart); //duplicate to home activity

        // Inflate the layout for this fragment
        return rootView;

    }

}
