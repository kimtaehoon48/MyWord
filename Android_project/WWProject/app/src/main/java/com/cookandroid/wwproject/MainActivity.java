package com.cookandroid.wwproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /** 오늘의 공부로 넘어가는 버튼 */
    Button btnToStudy;
    /** 단어 복습을 위한 리스트로 넘어가는 버튼 */
    Button btnToWord;
    /** 오늘의 단어로 단어 테스트(게임)으로 넘어가는 버튼 */
    Button btnWordTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 세 개의 버튼에 대한 초기화
        btnToStudy = (Button)findViewById(R.id.btnToStudy);
        btnToWord = (Button)findViewById(R.id.btnToWord);
        btnWordTest = (Button)findViewById(R.id.btnWordTest);

        btnToStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TodayActivity.class);
                startActivity(intent);
            }
        });
    }
}
