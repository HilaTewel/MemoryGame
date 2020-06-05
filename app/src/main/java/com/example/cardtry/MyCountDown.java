package com.example.cardtry;

import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MyCountDown {//create "show dialog" method in my activity
    private long timeInMillis;
    private TextView timerTV;
    private Game game;

    private CountDownTimer countDownTimer;
    private boolean isTimerRunning ;
    private long timeLeftInMillis;

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public void setTimeLeftInMillis(long timeLeftInMillis) {
        this.timeLeftInMillis = timeLeftInMillis;
    }

    public void setTimerRunning(boolean timerRunning) {
        isTimerRunning = timerRunning;
    }

    public MyCountDown(long timeInMillis, TextView timerTV, Game game) {
        this.timeInMillis = timeInMillis;
        this.timerTV = timerTV;
        this.game = game;
        timeLeftInMillis = timeInMillis;
        updateCountDownText();
    }

    public void startPause(){

        if(isTimerRunning){
            pauseTimer();

        }else{
            startTimer();
        }
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                game.showOutOfTimeDialog();
            }
        }.start();
        isTimerRunning = true;
    }

    private void pauseTimer(){
        countDownTimer.cancel();
        isTimerRunning = false;
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        timerTV.setText(timeLeftFormatted);
    }


}
