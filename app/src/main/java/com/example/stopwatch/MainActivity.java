package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.stopwatch.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    long timerValue = 0; //Timer which is initially set to 0
    boolean flag; //Flag to identify current timer state

    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            setTimerText();
            if (flag) {
                //Incrementing timer every seconds
                timerValue++;
                handler.postDelayed(runnable,1000);
            }

        }
    };

    //Method to display timer
    private void setTimerText() {
        long h = timerValue / 3600;
        long m = (timerValue % 3600) / 60;
        long s = timerValue % 60;

        //Setting time for hours, minutes, seconds
        String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", h, m, s);

        //set time
        mainBinding.tvTimer.setText(time);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inflating Main Activity
        mainBinding= ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();//pointing to root container of binding
        setContentView(view);


        //Start button click event
        mainBinding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Starting the timer by enabling the flag = true
                flag = true;

                //Start timer
                startTimer();

                //Disable start button/ enable true button
                mainBinding.btnStart.setEnabled(false);
                mainBinding.btnReset.setEnabled(true);
            }
        });

        //Stop button click event
        mainBinding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;

                //Disable stop button, enable start button
                mainBinding.btnStop.setEnabled(false);
                mainBinding.btnStart.setEnabled(true);

            }
        });

        //Reset button click event
        mainBinding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                timerValue = 0;

                //Calling the method to call set timer
                setTimerText();

                //Disable reset button, enable stop button
                mainBinding.btnReset.setEnabled(false);
                mainBinding.btnStop.setEnabled(true);
            }
        });
    }

    private void startTimer(){
        //Starting a timer with runnable every second
        handler.postDelayed(runnable,1000);
    }


    //decprecated method commented
    //  final Handler handler = new Handler();

}