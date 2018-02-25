package com.example.katiefitzgerald.anxietymanager;

//Pie chart tutorial used https://www.youtube.com/watch?v=8BcTXbwDGbg

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    String user_id;
    Button sensedAnxiety;
    Button addTodayAnxiety;
    Button calendarButton;
    Button cycleButton;
    Button profileButton;

    private int[] yData = {40, 30, 30};
    private String[] anxietyNamesData = {"College", "Social", "Money"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user_id = getIntent().getStringExtra("userId");

        setContentView(R.layout.activity_home);

        PieChart chart = findViewById(R.id.worriesChart);
        addDataSet(chart);

        sensedAnxiety = findViewById(R.id.sensedAnxiety);
        sensedAnxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sensedActivity = new Intent(getApplicationContext(), SensedActivity.class);
                sensedActivity.putExtra("userId", user_id);
                startActivity(sensedActivity);
            }
        });

        addTodayAnxiety = findViewById(R.id.addAnxiety);
        addTodayAnxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Questionnaire = new Intent(getApplicationContext(), WhatsUpActivity.class);
                startActivity(Questionnaire);
            }
        });

        calendarButton = findViewById(R.id.calendarButton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cycleButton = findViewById(R.id.cycleBtn);
        cycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        profileButton = findViewById(R.id.profileBtn);
        profileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
            }
        });
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
        color.add(Color.rgb(51,187,255));
        color.add(Color.rgb(255,153,153));
        color.add(Color.rgb(192,192,192));

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

        PieData pieData = new PieData(pieDataSet);

        chart.setData(pieData);
        chart.setTransparentCircleRadius(0);
        chart.setEnabled(true);
        chart.getDescription().setEnabled(false);
        chart.setRotationEnabled(false);
        chart.setHoleRadius(0);
        chart.invalidate();

    }

}
