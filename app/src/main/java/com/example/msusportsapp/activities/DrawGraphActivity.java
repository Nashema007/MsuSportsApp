package com.example.msusportsapp.activities;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.msusportsapp.R;
import com.example.msusportsapp.utilities.DrawGraph;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.extensions.builders.SciChartBuilder;

import java.util.ArrayList;


public class DrawGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_graph);

        LinearLayout chartLayout = findViewById(R.id.perTeamCharLayout);

        SciChartSurface surface = new SciChartSurface(this);

        // Add the SciChartSurface to the layout
        chartLayout.addView(surface);

        // Initialize the SciChartBuilder
        SciChartBuilder.init(this);

        // Obtain the SciChartBuilder instance
        final SciChartBuilder sciChartBuilder = SciChartBuilder.instance();

        ArrayList<Integer> yAxis = getIntent().getIntegerArrayListExtra("yAxis");

        ArrayList<Integer> xAxis = new ArrayList<>();
        xAxis.add(1);
        xAxis.add(2);
        xAxis.add(3);
        xAxis.add(4);
        xAxis.add(5);

        String stats = getIntent().getStringExtra("statsLevel");
        String rating = getIntent().getStringExtra("rating");

        if(stats == null|| stats.equals("")){
            stats ="";
            drawGraph(stats,rating,surface, sciChartBuilder, xAxis, yAxis);
        }
        else if(rating == null || rating.equals("")){
            rating = "";
            drawGraph(stats,rating,surface, sciChartBuilder, xAxis, yAxis);
        }

    }


    private void drawGraph (String stats, String rating, SciChartSurface surface,
                            SciChartBuilder sciChartBuilder, ArrayList<Integer> xAxis,
                            ArrayList<Integer> yAxis){

        if(stats.equals("statsLevel")){
            DrawGraph.columnGraph(surface,sciChartBuilder, "Levels", "No. of Players", xAxis, yAxis);
        }
        else if(rating.equals("rating")){

            DrawGraph.columnGraph(surface,sciChartBuilder, "Ratings", "People who like the App", xAxis, yAxis);

        }

    }
}
