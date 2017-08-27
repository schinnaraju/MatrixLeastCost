package com.matrix.leastcost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LeastPath extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_least_path);
        Intent leastData = getIntent();
        ((TextView) findViewById(R.id.isValidPath)).setText(leastData.getStringExtra("isValidInput"));
        int leastCost = leastData.getIntExtra("leastCost", 0);
        if (leastCost > 0) {
            ((TextView) findViewById(R.id.leastCost)).setText(leastCost + "");
            ((TextView) findViewById(R.id.leastPath)).setText(leastData.getStringExtra("leastPath"));
        }
    }
}
