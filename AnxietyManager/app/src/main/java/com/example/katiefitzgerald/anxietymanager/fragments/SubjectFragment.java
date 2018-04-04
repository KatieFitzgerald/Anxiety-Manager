package com.example.katiefitzgerald.anxietymanager.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.activities.HomeActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

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
        addDataSet(subjectChart); //duplicate to home activity

        // Inflate the layout for this fragment
        return rootView;

    }

    public static void addDataSet(PieChart chart) {

        int[] yData = {35, 25, 40};
        String[] anxietyNamesData = {"Upcoming Exams", "Assignment", "Getting Bus"};

        ArrayList<PieEntry> yEntries = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            yEntries.add(new PieEntry(yData[i], i));
        }

        PieDataSet pieDataSet = new PieDataSet(yEntries, "");
        pieDataSet.setSliceSpace(1);
        pieDataSet.setValueTextSize(12);

        //add color to dataset
        ArrayList<Integer> color = new ArrayList<>();
        color.add(Color.rgb(145, 243, 247));
        color.add(Color.rgb(250, 220, 251));
        color.add(Color.rgb(174, 249, 228));

        pieDataSet.setColors(color);

        List<LegendEntry> entries = new ArrayList<>();

        for (int i = 0; i < anxietyNamesData.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = color.get(i);
            entry.label = anxietyNamesData[i];
            entries.add(entry);
        }

        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextColor(Color.rgb(255, 255, 255));
        legend.setCustom(entries);

        PieData pieData = new PieData(pieDataSet);

        chart.setTouchEnabled(false);
        chart.setData(pieData);
        chart.setTransparentCircleRadius(0);
        chart.setEnabled(true);
        chart.getDescription().setEnabled(false);
        chart.setRotationEnabled(false);
        chart.setHoleRadius(0);
        chart.invalidate();

    }
}
