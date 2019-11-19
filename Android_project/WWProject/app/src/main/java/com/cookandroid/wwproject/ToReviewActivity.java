package com.cookandroid.wwproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.GregorianCalendar;
import java.util.Random;

/**
 *
 */
public class ToReviewActivity extends Activity {

    /** 단어를 저장하는 TextView 선언 */
    TextView tvWord;

    /* 네 개의 뜻을 저장해줄 TextView 선언 */
    Button btnAnswer1;
    Button btnAnswer2;
    Button btnAnswer3;
    Button btnAnswer4;

    Random rm = new Random();
    int[] random = new int[4];

    GregorianCalendar today = new GregorianCalendar ( );
    int day = today.get ( today.DAY_OF_MONTH );
    int pos = day * 10 - 11;

    /* 필요한 정보 목록
     *
     * 1. 오늘의 공부에 저장된 10개의 단어가 저장되어 있는 배열
     * 2. 거짓 답변으로 적을 뜻만 저장되어 있는 배열
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_review);
        setTitle("복습");

        if(day == 0) {
            pos = 0;
        }

        // 초기화
        TextView tvWord = (TextView)findViewById(R.id.tvWord);

        btnAnswer1 = (Button)findViewById(R.id.btnAnswer1);
        btnAnswer2 = (Button)findViewById(R.id.btnAnswer2);
        btnAnswer3 = (Button)findViewById(R.id.btnAnswer3);
        btnAnswer4 = (Button)findViewById(R.id.btnAnswer4);
        TextView[] tvCounts = new TextView[11];

        Integer tvCountId[] = {R.id.tvCount0, R.id.tvCount1, R.id.tvCount2, R.id.tvCount3, R.id.tvCount4, R.id.tvCount5, R.id.tvCount6, R.id.tvCount7, R.id.tvCount8, R.id.tvCount9, R.id.tvCount9};
        for (int idx = 0; idx < 11; idx++) {
            tvCounts[idx] = (TextView)findViewById(tvCountId[idx]);
        }

        final Intent intent = getIntent();
        final int cnt = intent.getIntExtra("count", 0);
        final String words[] = intent.getStringArrayExtra("words");
        final String meanings[] = intent.getStringArrayExtra("meanings");
        final boolean isCorrect[] = intent.getBooleanArrayExtra("correct");

        pos =  day * 10 - 11 + cnt;

        tvWord.setText(words[pos]);
        for (int idx = 0; idx < 4; idx++) {
            random[idx] = rm.nextInt(100);
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
        btnAnswer1.setText("" + meanings[random[0]]);
        btnAnswer2.setText("" + meanings[random[1]]);
        btnAnswer3.setText("" + meanings[random[2]]);
        btnAnswer4.setText("" + meanings[random[3]]);


        for (int idx = 0; idx <= cnt; idx++) {
            tvCounts[idx].setVisibility(View.VISIBLE);
        }


        final int count = cnt + 1;

        // TODO 정답을 저장해서 보내야 한다.

        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos == random[0]) {
                    Toast.makeText(getApplicationContext(), "정답", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "오답", Toast.LENGTH_SHORT).show();
                }

                // count 가 몇인지 모름
                if (count == 10) {
                    Intent resultIntent = new Intent(getApplicationContext(), ToReslutActivity.class);
                    //intent.putExtra("words", wordss);
                    //intent.putExtra("meanings", meanings);
                    startActivity(resultIntent);
                }
                if (count != 10) {
                    Intent todayIntent = new Intent(getApplicationContext(), TodayActivity.class);
                    todayIntent.putExtra("cnt", count);
                    startActivity(todayIntent);
                }
            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos == random[1]) {
                    Toast.makeText(getApplicationContext(), "정답", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "오답", Toast.LENGTH_SHORT).show();
                }

                // count 가 몇인지 모름
                if (count == 10) {
                    Intent resultIntent = new Intent(getApplicationContext(), ToReslutActivity.class);
                    //intent.putExtra("words", wordss);
                    //intent.putExtra("meanings", meanings);
                    startActivity(resultIntent);
                }
                if (count != 10) {
                    Intent todayIntent = new Intent(getApplicationContext(), TodayActivity.class);
                    todayIntent.putExtra("cnt", count);
                    startActivity(todayIntent);
                }
            }
        });

        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos == random[2]) {
                    Toast.makeText(getApplicationContext(), "정답", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "오답", Toast.LENGTH_SHORT).show();
                }

                // count 가 몇인지 모름
                if (count == 10) {
                    Intent resultIntent = new Intent(getApplicationContext(), ToReslutActivity.class);
                    //intent.putExtra("words", wordss);
                    //intent.putExtra("meanings", meanings);
                    startActivity(resultIntent);
                }
                if (count != 10) {
                    Intent todayIntent = new Intent(getApplicationContext(), TodayActivity.class);
                    todayIntent.putExtra("cnt", count);
                    startActivity(todayIntent);
                }
            }
        });

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos == random[3]) {
                    Toast.makeText(getApplicationContext(), "정답", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "오답", Toast.LENGTH_SHORT).show();
                }

                // count 가 몇인지 모름
                if (count == 10) {
                    Intent resultIntent = new Intent(getApplicationContext(), ToReslutActivity.class);
                    //intent.putExtra("words", wordss);
                    //intent.putExtra("meanings", meanings);
                    startActivity(resultIntent);
                }
                if (count != 10) {
                    Intent todayIntent = new Intent(getApplicationContext(), TodayActivity.class);
                    todayIntent.putExtra("cnt", count);
                    startActivity(todayIntent);
                }
            }
        });




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
}
