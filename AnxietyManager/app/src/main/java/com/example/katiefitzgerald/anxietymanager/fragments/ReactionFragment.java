package com.example.katiefitzgerald.anxietymanager.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katiefitzgerald.anxietymanager.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

/**
 * Created by Katie Fitzgerald on 28/03/2018.
 */

public class ReactionFragment extends Fragment {

    private LineChart reactionChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.reaction_fragment, container, false);

        reactionChart = rootView.findViewById(R.id.reactionChart);
        addDataSet();

        // Inflate the layout for this fragment
        return rootView;
    }

    private void addDataSet() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0, 0));
        entries.add(new Entry(1, 1));
        entries.add(new Entry(2, 1.5f));
        entries.add(new Entry(3, 1));
        entries.add(new Entry(4, 2));
        entries.add(new Entry(5, 3));
        entries.add(new Entry(6, 3));

        LineDataSet lineDataSet = new LineDataSet(entries, "Correlation between mood and reaction");
        lineDataSet.setLineWidth(2);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);

        YAxis leftAxis = reactionChart.getAxisLeft();
        leftAxis.setEnabled(true);
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        YAxis rightAxis = reactionChart.getAxisRight();
        rightAxis.setEnabled(false);

        Legend legend = reactionChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextColor(Color.rgb(255, 255, 255));
        legend.setTextSize(12);

        XAxis xAxis = reactionChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setTextColor(Color.WHITE);

        reactionChart.getDescription().setText(" ");
        reactionChart.setDrawMarkers(false);
        reactionChart.getAxisLeft().setDrawGridLines(false);
        reactionChart.setData(lineData);

    }

}
