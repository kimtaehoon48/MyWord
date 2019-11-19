package com.cookandroid.lockscreen;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.ncorti.slidetoact.SlideToActView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class LockActivity extends Activity {

    static String[] words = new String[200];
    static String[] meaning = new String[200];

    GregorianCalendar today = new GregorianCalendar ( );
    int day = today.get ( today.DAY_OF_MONTH );
    int pos = day * 10 - 11;

    SlideToActView slide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

//        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
//        KeyguardLock keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);
//
//        keyLock.disableKeyguard();    // 기본 잠금화면 없애기
//        keyLock.reenableKeyguard();

        setContentView(R.layout.lock);

        readWordData();

        if(day == 0) {
            pos = 0;
        }

        ListView list = (ListView)findViewById(R.id.listLock);

        final ArrayList<String> Items = new ArrayList<String>(); // 빈 데이터 리스트 생성

        for (int idx = 1; idx < 11; idx++) {
            Items.add(words[pos] + "    " + meaning[pos]);
            pos++;
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Items); //어뎁터 생성

        // 리스트뷰 생성 및 어뎁터 지정
        list.setAdapter(adapter);

        // 슬라이스 관련
        slide = (SlideToActView)findViewById(R.id.slide);
        slide.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public  void onSlideComplete(SlideToActView slideToActView) {
                // 세번밖에 안됨
                finishAffinity();
//                finish();
            }
        });


    }

    private void readWordData() {
        InputStream inS = getResources().openRawResource(R.raw.word);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inS, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            // Step over headers
            //reader.readLine();
            int count = 0;
            while ((line = reader.readLine()) != null) {

                // Split by ','
                String[] tokens = line.split(",");

                // read the data
                words[count] = tokens[0];
                meaning[count] = tokens[1];

                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
