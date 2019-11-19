package com.cookandroid.lockscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {

    /** 오늘의 공부 버튼 클릭 */
    Button btnToday;

    /** 복습 버튼 클릭 */
    Button btnReview;

    /** 설정 버튼 클릭 */
    Button btnSetup;

    /** sql테스트 버튼 */
    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 버튼 초기화
        btnToday = (Button)findViewById(R.id.btnToday);
        btnReview = (Button)findViewById(R.id.btnReview);
        btnSetup = (Button)findViewById(R.id.btnSetup);
        btnTest = (Button)findViewById(R.id.btnTest);

        /**
         * btnToday 오늘의 공부 버튼을 눌렀을 때의 동작
         * 오늘의 공부액티비티로 넘어간다.
         */
        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TodayActivity.class);
                startActivity(intent);
            }
        });

        /**
         * btnReview 복습 버튼을 눌렀을 때의 동작
         * 오늘의 공부액티비티로 넘어간다.
         */
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), Review.class);
                startActivity(intent1);
            }
        });

        /**
         * 설정버튼 누르면!!
         */
        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), SetupAcivity.class);
                startActivity(intent2);
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), DataActivity.class);
                startActivity(intent3);
            }
        });


    }

}
