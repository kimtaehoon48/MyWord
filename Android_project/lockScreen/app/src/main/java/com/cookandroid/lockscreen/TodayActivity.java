package com.cookandroid.lockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 오늘의 단어
 *
 * - 오늘 공부할 단어가 나타난 화면 과
 *   그 단어에 대한 복습을 번갈아 화면 전환
 */
public class TodayActivity extends Activity {

    /** 오늘의 단어의 영어단어를 저장할 TextView 선언 */


    /** 오늘의 단어의 뜻을 저장할 TextView 선언 */
    TextView tvMeaning;

    /* 받아야할 정보
     *
     * 1. 10개의 오늘의 단어
     */

    // TODO 뒤로 갔을 때 메인화면으로 가도록 설정
    // TODO 데이터베이스 안드로이드 스튜디오랑 연결하기
    // TODO 데이터베이스 단어 체우기

    static String[] words = new String[200];
    static String[] meaning = new String[200];

    GregorianCalendar today = new GregorianCalendar ( );
    int day = today.get ( today.DAY_OF_MONTH );
    int pos = day * 10 - 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_study);
        setTitle("오늘의 공부");


        readWordData();

        /** 영어 단어 저장할 스트링배열 선언, 초기화 */
        final String[] word = new String[11];
        /** 뜻 저장할 스트링배열 선언, 초기화 */
        final String[] mean = new String[11];

        if(day == 0) {
            pos = 0;
        }

        // 일단 아무 단어나 체워본다.
        for(int idx = 1; idx < 11; idx++) {
            word[idx] = words[pos];
            mean[idx] = meaning[pos];
            pos++;
        }

        // TODO 배열 11로 바꾸고 0에는 무조건 0 집어넣고 그 다음부터 단어 집어넣는 걸로 하자
//        final TextView tvWords[] = new TextView[11];
//        final TextView tvMeanings[] = new TextView[11];
        final TextView tvCounts[] = new TextView[11];


//        Integer tvWordId[] = {R.id.tvWord0, R.id.tvWord1, R.id.tvWord2, R.id.tvWord3, R.id.tvWord4, R.id.tvWord5, R.id.tvWord6, R.id.tvWord7, R.id.tvWord8, R.id.tvWord9, R.id.tvWord9};
//        Integer tvMeaningId[] = {R.id.tvMeaning0, R.id.tvMeaning1, R.id.tvMeaning2, R.id.tvMeaning3, R.id.tvMeaning4, R.id.tvMeaning5, R.id.tvMeaning6, R.id.tvMeaning7, R.id.tvMeaning8, R.id.tvMeaning9,  R.id.tvMeaning9};
        Integer tvCountId[] = {R.id.tvCount0, R.id.tvCount1, R.id.tvCount2, R.id.tvCount3, R.id.tvCount4, R.id.tvCount5, R.id.tvCount6, R.id.tvCount7, R.id.tvCount8, R.id.tvCount9, R.id.tvCount9};
//
        for (int idx = 0; idx < 11; idx++) {
            tvCounts[idx] = (TextView)findViewById(tvCountId[idx]);
            // 일단 다 false 로 초기화
            //isCorrect[idx] = false;
        }

        TextView tvWord = (TextView)findViewById(R.id.tvWord0);
        TextView tvMeaning = (TextView)findViewById(R.id.tvMeaning0);

        tvWord.setText(word[1]);
        tvMeaning.setText(mean[1]);

        int cnt = 0;
        Intent intent = getIntent();
        cnt = intent.getIntExtra("cnt", 0);


        final int count = cnt;//cnt;

        if(count != 0) {
            tvWord.setText(word[count + 1]);
            tvMeaning.setText(mean[count + 1]);
        }

        for (int idx = 0; idx <= count; idx++) {
            tvCounts[idx].setVisibility(View.VISIBLE);
        }

//        if (count != 0) {
//            tvWords[count - 1].setVisibility(View.INVISIBLE);
//            tvMeanings[count - 1].setVisibility(View.INVISIBLE);
//            //tvWords[count].setText(count);
//
//            tvWords[0].setVisibility(View.INVISIBLE);
//            tvMeanings[0].setVisibility(View.INVISIBLE);
//
//            tvWords[count].setVisibility(View.VISIBLE);
//            tvMeanings[count].setVisibility(View.VISIBLE);
//        }
//
//        if (count == 10) {
//            tvWords[count - 1].setVisibility(View.INVISIBLE);
//            tvMeanings[count - 1].setVisibility(View.INVISIBLE);
//
//            tvWords[count].setVisibility(View.VISIBLE);
//            tvMeanings[count].setVisibility(View.VISIBLE);
//        }
//
//
//        for (int idx = 0; idx <= count; idx++) {
//            tvCounts[idx].setVisibility(View.VISIBLE);
//        }



        Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

//                boolean[] isCorr = new boolean[11];
//                for (int idx = 1; idx < 11; idx++) {
//                    isCorr[idx] = isCorrect[idx];
//                }
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                intent.putExtra("count", count);
                intent.putExtra("words", words);
                intent.putExtra("meanings", meaning);

                startActivity(intent);
            }
        };
        handler.sendEmptyMessageDelayed(0, 2000);

    }

    /**
     * 븨로가기 버튼 눌렀을 떄 동작을 정의
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }


    private List<WordSample> wordSamples = new ArrayList<>();
    private void readWordData() {
        InputStream inS = getResources().openRawResource(R.raw.word);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inS, Charset.forName("UTF-8"))
        );

        String line = "";
        try{
            // Step over headers
            //reader.readLine();
            int count = 0;
            while ((line = reader.readLine()) != null){
                Log.d("MyAcitivity", "Line: " + line);

                // Split by ','
                String[] tokens = line.split(",");

                // read the data
                WordSample sample = new WordSample();
                words[count] = tokens[0];
                meaning[count] = tokens[1];
                sample.setWord(tokens[0]);
                sample.setMeaning(tokens[1]);

                wordSamples.add(sample);

                Log.d("MyAcitivity", "Just create: " + sample);
                count++;
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }

    }
}
