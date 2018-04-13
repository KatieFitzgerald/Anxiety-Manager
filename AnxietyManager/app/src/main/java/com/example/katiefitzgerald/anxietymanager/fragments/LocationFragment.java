package com.example.katiefitzgerald.anxietymanager.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katiefitzgerald.anxietymanager.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katie Fitzgerald on 28/03/2018.
 */

public class LocationFragment extends Fragment {

    private HorizontalBarChart locationChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_fragment, container, false);

        locationChart = rootView.findViewById(R.id.locationChart);
        addDataSet();

        // Inflate the layout for this fragment
        return rootView;
    }

    private void addDataSet() {

        ArrayList<BarEntry> points = new ArrayList<>();
        points.add(new BarEntry(3, 9));
        points.add(new BarEntry(2, 6));
        points.add(new BarEntry(1, 4));

        String[] labels = new String[] {"Kevin St", "Questionnaire", "Forest Park"};

        BarDataSet barDataSet = new BarDataSet(points, "");
        barDataSet.setDrawValues(false);

        //add color to dataset
        ArrayList<Integer> color = new ArrayList<>();
        color.add(Color.rgb(145, 243, 247));
        color.add(Color.rgb(250, 220, 251));
        color.add(Color.rgb(174, 249, 228));

        barDataSet.setColors(color);

        List<LegendEntry> entries = new ArrayList<>();

        for (int i = 0; i < labels.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = color.get(i);
            entry.label = labels[i];
            entries.add(entry);
        }

        Legend legend = locationChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextColor(Color.rgb(255, 255, 255));
        legend.setTextSize(12);
        legend.setCustom(entries);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.4f);

        YAxis leftAxis = locationChart.getAxisLeft();
        leftAxis.setEnabled(false);

        YAxis rightAxis = locationChart.getAxisRight();
        rightAxis.setEnabled(true);
        rightAxis.setAxisLineColor(Color.WHITE);
        rightAxis.setTextColor(Color.WHITE);
        rightAxis.setAxisMinimum(0.0f);
        rightAxis.setAxisMaximum(10.0f);
        rightAxis.setDrawGridLines(false);

        XAxis xAxis = locationChart.getXAxis();
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(3.5f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        locationChart.getDescription().setEnabled(false);
        locationChart.setDrawMarkers(false);
        locationChart.setScaleEnabled(false);
        locationChart.setPinchZoom(false);
        locationChart.setDoubleTapToZoomEnabled(false);
        locationChart.setData(barData);

    }

}