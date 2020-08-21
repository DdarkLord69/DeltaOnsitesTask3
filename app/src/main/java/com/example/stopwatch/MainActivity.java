package com.example.stopwatch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ClockView clockView;
    private Button start;
    private Button pause;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clockView = findViewById(R.id.clock_view);
        start = findViewById(R.id.btn_start);
        pause = findViewById(R.id.btn_pause);
        reset = findViewById(R.id.btn_reset);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!clockView.isRunning) {
                    clockView.isRunning = true;
                    clockView.postInvalidate();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clockView.isRunning = false;
                clockView.i--;
                clockView.s--;
                clockView.postInvalidate();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clockView.isRunning = false;
                clockView.i = -900;
                clockView.s = -15;
                clockView.postInvalidate();
            }
        });
    }
}