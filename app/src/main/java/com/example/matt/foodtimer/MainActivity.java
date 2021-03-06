package com.example.matt.foodtimer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekbar;
    TextView timerTextView;
    Button controllerButton;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    public void resetTimer ()
    {
        timerTextView.setText("0:30");
        timerSeekbar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Go!");
        timerSeekbar.setEnabled(true);
        counterIsActive = false;
    }

    public void updateTimer (int secondsLeft)
    {
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9)
        {
            secondString = "0" + secondString;
        }else if(seconds == 0)
        {
            secondString = "00";
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }


    public void controlTimer (View view)
    {
        if (counterIsActive == false)
        {
        counterIsActive = true;
        timerSeekbar.setEnabled(false);
        controllerButton.setText("Stop");

        countDownTimer = new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000) {
            @Override
            public void onTick(long l) {

                updateTimer((int) l / 1000);
            }

            @Override
            public void onFinish() {

                resetTimer();
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.bomb);
                mplayer.start();

            }
        }.start();

    }else
        {
            resetTimer();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekbar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        controllerButton = (Button) findViewById(R.id.controllerButton);

        timerSeekbar.setMax(1800);
        timerSeekbar.setProgress(30);

        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}
