package com.cookandroid.lockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class Review extends Activity {

    /** 단어 저장된 배열 */
    static String[] words = new String[200];
    static String[] meaning = new String[200];

    TextView tvWord;

    Button btnAnswer1;
    Button btnAnswer2;
    Button btnAnswer3;
    Button btnAnswer4;

    TextView[] tvCounts  = new TextView[10];

    Random rm = new Random();

    // 날짜는 pos로 구분한다.
    int pos = 0;
    int cnt = 1;
    int[] random = new int[4];

    /** 정답을 맞췄는지 틀렸는지를 저장할 배열 선언 */
    boolean[] isCorrect = new boolean[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);

        readWordData();
        final Integer tvCountId[] = {R.id.tvCount0, R.id.tvCount1, R.id.tvCount2, R.id.tvCount3, R.id.tvCount4, R.id.tvCount5, R.id.tvCount6, R.id.tvCount7, R.id.tvCount8, R.id.tvCount9, R.id.tvCount9};

        tvWord = (TextView) findViewById(R.id.tvWord0);
        btnAnswer1 = (Button)findViewById(R.id.btnAnswer01);
        btnAnswer2 = (Button)findViewById(R.id.btnAnswer02);
        btnAnswer3 = (Button)findViewById(R.id.btnAnswer03);
        btnAnswer4 = (Button)findViewById(R.id.btnAnswer04);

        for (int idx = 0; idx < 10; idx++) {
            tvCounts[idx] = (TextView)findViewById(tvCountId[idx]);
            // 일단 다 false 로 초기화
            isCorrect[idx] = false;
        }

        tvWord.setText(words[pos]);
        btnAnswer1.setText(meaning[5]);
        btnAnswer2.setText(meaning[3]);
        btnAnswer3.setText(meaning[pos]);
        btnAnswer4.setText(meaning[4]);

        random[0] = 5;
        random[1] = 3;
        random[2] = pos;
        random[3] = 4;


        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt != 10) {
                    tvCounts[cnt].setVisibility(View.VISIBLE);
                }
                cnt++;

                if (pos == random[0]){
                    Toast.makeText(getApplicationContext(), "정답", Toast.LENGTH_SHORT).show();
                    if(pos != 10) {
                        isCorrect[pos] = true;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "오답" + pos + random[0], Toast.LENGTH_SHORT).show();
                    //isCorrect[pos] = false;
                }

                pos++;

                for (int idx = 0; idx < 4; idx++) {
                    random[idx] = rm.nextInt(9);
                    if (idx != 0) {
                        for (int ind = idx - 1; ind >= 0; ind--) {
                            if (random[ind] == random[idx] || random[idx] == pos) {
                                idx--;
                                continue;
                            }
                        }
                    }
                }
                int cor = rm.nextInt(3);
                random[cor] = pos;

                if (cnt != 11) {
                    tvWord.setText("" + words[pos]);
                    btnAnswer1.setText("" + meaning[random[0]]);
                    btnAnswer2.setText("" + meaning[random[1]]);
                    btnAnswer3.setText("" + meaning[random[2]]);
                    btnAnswer4.setText("" + meaning[random[3]]);
                }

                if (cnt == 11) {
                    boolean[] isCorr = new boolean[11];
                    for (int idx = 1; idx < 11; idx++) {
                        isCorr[idx] = isCorrect[idx - 1];
                    }
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("pos", pos - 8);
                    intent.putExtra("correct", isCorr); // 정답과 오답
                    startActivity(intent);
                }
            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt != 10) {
                    tvCounts[cnt].setVisibility(View.VISIBLE);
                }
                cnt++;

                if (pos == random[1]){
                    Toast.makeText(getApplicationContext(), "정답", Toast.LENGTH_SHORT).show();
                    if(pos != 10) {
                        isCorrect[pos] = true;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "오답", Toast.LENGTH_SHORT).show();
                    //isCorrect[pos] = false;
                }

                pos++;

                for (int idx = 0; idx < 4; idx++) {
                    random[idx] = rm.nextInt(9);
                    if (idx != 0) {
                        for (int ind = idx - 1; ind >= 0; ind--) {
                            if (random[ind] == random[idx] || random[idx] == pos) {
                                idx--;
                                continue;
                            }
                        }
                    }
                }
                int cor = rm.nextInt(3);
                random[cor] = pos;

                if (cnt != 11) {
                    tvWord.setText("" + words[pos]);
                    btnAnswer1.setText("" + meaning[random[0]]);
                    btnAnswer2.setText("" + meaning[random[1]]);
                    btnAnswer3.setText("" + meaning[random[2]]);
                    btnAnswer4.setText("" + meaning[random[3]]);
                }

                if (cnt == 11) {
                    boolean[] isCorr = new boolean[11];
                    for (int idx = 1; idx < 11; idx++) {
                        isCorr[idx] = isCorrect[idx - 1];
                    }
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("pos", pos - 8);
                    intent.putExtra("correct", isCorr); // 정답과 오답
                    startActivity(intent);
                }
            }
        });

        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt != 10) {
                    tvCounts[cnt].setVisibility(View.VISIBLE);
                }
                cnt++;

                if (pos == random[2]){
                    Toast.makeText(getApplicationContext(), "정답", Toast.LENGTH_SHORT).show();
                    if(pos != 10) {
                        isCorrect[pos] = true;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "오답", Toast.LENGTH_SHORT).show();
                    //isCorrect[pos] = false;
                }

                pos++;

                for (int idx = 0; idx < 4; idx++) {
                    random[idx] = rm.nextInt(9);
                    if (idx != 0) {
                        for (int ind = idx - 1; ind >= 0; ind--) {
                            if (random[ind] == random[idx] || random[idx] == pos) {
                                idx--;
                                continue;
                            }
                        }
                    }
                }
                int cor = rm.nextInt(3);
                random[cor] = pos;

                if (cnt != 11) {
                    tvWord.setText("" + words[pos]);
                    btnAnswer1.setText("" + meaning[random[0]]);
                    btnAnswer2.setText("" + meaning[random[1]]);
                    btnAnswer3.setText("" + meaning[random[2]]);
                    btnAnswer4.setText("" + meaning[random[3]]);
                }

                if (cnt == 11) {
                    boolean[] isCorr = new boolean[11];
                    for (int idx = 1; idx < 11; idx++) {
                        isCorr[idx] = isCorrect[idx - 1];
                    }
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("pos", pos - 8);
                    intent.putExtra("correct", isCorr); // 정답과 오답
                    startActivity(intent);
                }
            }
        });

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt != 10) {
                    tvCounts[cnt].setVisibility(View.VISIBLE);
                }
                cnt++;

                if (pos == random[3]){
                    Toast.makeText(getApplicationContext(), "정답", Toast.LENGTH_SHORT).show();
                    if(pos != 10) {
                        isCorrect[pos] = true;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "오답", Toast.LENGTH_SHORT).show();
                    //isCorrect[pos] = false;
                }

                pos++;

                for (int idx = 0; idx < 4; idx++) {
                    random[idx] = rm.nextInt(9);
                    if (idx != 0) {
                        for (int ind = idx - 1; ind >= 0; ind--) {
                            if (random[ind] == random[idx] || random[idx] == pos) {
                                idx--;
                                continue;
                            }
                        }
                    }
                }
                int cor = rm.nextInt(3);
                random[cor] = pos;

                if (cnt != 11) {
                    tvWord.setText("" + words[pos]);
                    btnAnswer1.setText("" + meaning[random[0]]);
                    btnAnswer2.setText("" + meaning[random[1]]);
                    btnAnswer3.setText("" + meaning[random[2]]);
                    btnAnswer4.setText("" + meaning[random[3]]);
                }

                if (cnt == 11) {
                    boolean[] isCorr = new boolean[11];
                    for (int idx = 1; idx < 11; idx++) {
                        isCorr[idx] = isCorrect[idx - 1];
                    }
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("pos", pos - 8);
                    intent.putExtra("correct", isCorr); // 정답과 오답
                    startActivity(intent);
                }
            }
        });


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
                words[count] = tokens[0];
                meaning[count] = tokens[1];

                count++;
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }

    }



}
