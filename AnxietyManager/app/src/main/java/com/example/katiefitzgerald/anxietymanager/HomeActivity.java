package com.example.katiefitzgerald.anxietymanager;

//Pie chart tutorial used https://www.youtube.com/watch?v=8BcTXbwDGbg

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.CYAN;
import static android.graphics.Color.LTGRAY;

public class HomeActivity extends AppCompatActivity {

    private static String TAG = "HomeActivity";

    String user_id;
    Button sensed;

    private int[] yData = {50, 20, 30};
    private String[] anxietyNamesData = {"College", "Social", "Money"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: starting to create chart");

        user_id = getIntent().getStringExtra("userId");

        setContentView(R.layout.activity_home);

        sensed = findViewById(R.id.sensedAnxiety);
        sensed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sensedActivity = new Intent(getApplicationContext(), SensedActivity.class);
                sensedActivity.putExtra("userId", user_id);
                startActivity(sensedActivity);
            }
        });

        PieChart chart = findViewById(R.id.worriesChart);
        addDataSet(chart);
    }

    private void addDataSet(PieChart chart) {

        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();

        for(int i = 0; i < yData.length; i++) {
            yEntries.add(new PieEntry(yData[i],i));
        }

        PieDataSet pieDataSet = new PieDataSet(yEntries, "");
        pieDataSet.setSliceSpace(1);
        pieDataSet.setValueTextSize(12);

        //add color to dataset
        ArrayList<Integer> color = new ArrayList<>();
        color.add(LTGRAY);
        color.add(BLUE);
        color.add(CYAN);

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
        legend.setCustom(entries);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);

        chart.setData(pieData);
        chart.setTransparentCircleRadius(0);
        chart.setEnabled(true);
        chart.setRotationEnabled(false);
        chart.setHoleRadius(0);
        chart.invalidate();

    }

}
