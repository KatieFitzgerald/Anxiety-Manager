package com.example.katiefitzgerald.anxietymanager.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katiefitzgerald.anxietymanager.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

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

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(3, 9));
        entries.add(new BarEntry(2, 6));
        entries.add(new BarEntry(1, 4));

        int[] colors = new int[] {Color.rgb(11, 94, 237), Color.rgb(42, 188, 237), Color.rgb(111, 224, 226)};
        String[] labels = new String[] {"Kevin St", "Questionnaire", "Forest Park"};

        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColors(colors);
        barDataSet.setDrawValues(false);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.4f);

        YAxis leftAxis = locationChart.getAxisLeft();
        leftAxis.setEnabled(false);
        YAxis rightAxis = locationChart.getAxisRight();
        rightAxis.setEnabled(true);
        rightAxis.setDrawGridLines(false);
        XAxis xAxis = locationChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        locationChart.getDescription().setEnabled(false);
        locationChart.setDrawMarkers(false);
        locationChart.setScaleEnabled(false);
        locationChart.setPinchZoom(false);
        locationChart.setDoubleTapToZoomEnabled(false);
        locationChart.setData(barData);

        Legend legend = locationChart.getLegend();
        legend.setEnabled(true);
        legend.setTextColor(Color.WHITE);
        legend.setExtra(colors, labels);
    }

}
