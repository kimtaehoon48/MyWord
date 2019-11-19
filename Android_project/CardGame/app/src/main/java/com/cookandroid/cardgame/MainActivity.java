package com.cookandroid.cardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button[] wordCards = new Button[4];
    Button[] meanCards = new Button[4];
    TextView count_txt;

    private static final int TOTAL_CARD_NUM = 8;


    private static final int MILLISINFUTURE = 11*1000;
    private static final int COUNT_DOWN_INTERVAL = 1000;
    private int count = 10;
    private TextView countTxt ;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////////////////////////////////////////////////////////////////////////////////////
        // 시험 배열
        String[] words = new String[5];
        String[] meanings = new String[5];

        for(int idx = 0; idx < words.length; idx++) {
            words[idx] = "" + idx;
            meanings[idx] = "" + idx;
        }
        /////////////////////////////////////////////////////////////////////////////////////

        Integer[] wordCardId = {R.id.word_one, R.id.word_two, R.id.word_there, R.id.word_four, R.id.word_five};
        Integer[] meanCardId = {R.id.mean_one, R.id.mean_two, R.id.mean_there, R.id.mean_four, R.id.mean_five};

        countTxt = (TextView)findViewById(R.id.count_txt);
        countDownTimer();
        countDownTimer.start();

        for(int idx = 0; idx < wordCards.length; idx++) {
            wordCards[idx] = (Button)findViewById(wordCardId[idx]);
            wordCards[idx].setText(words[idx]);
            meanCards[idx] = (Button)findViewById(meanCardId[idx]);
            meanCards[idx].setText(meanings[idx]);
        }

        wordCards[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordCards[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "눌린다", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    public void countDownTimer(){

        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            public void onTick(long millisUntilFinished) {
                countTxt.setText(String.valueOf(count));
                count --;
            }
            public void onFinish() {
                countTxt.setText(String.valueOf("TIME OVER"));
            }
        };
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            countDownTimer.cancel();
        } catch (Exception e) {}
        countDownTimer=null;
    }

}
