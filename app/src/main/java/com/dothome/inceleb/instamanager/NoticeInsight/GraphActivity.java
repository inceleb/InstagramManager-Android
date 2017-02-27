package com.dothome.inceleb.instamanager.NoticeInsight;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dothome.inceleb.instamanager.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph2);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 4),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 3),
                new DataPoint(6, 1)
        });
        series.setColor(Color.parseColor("#b485f2"));
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(5);

        graph.addSeries(series);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.parseColor("#777777"));
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.parseColor("#777777"));
        graph.getGridLabelRenderer().setTextSize(30);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(6);

    }
}
