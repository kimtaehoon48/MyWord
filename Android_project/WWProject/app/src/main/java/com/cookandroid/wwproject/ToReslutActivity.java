package com.cookandroid.wwproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ToReslutActivity extends Activity {

    static String[] words = new String[200];
    static String[] meaning = new String[200];

    GregorianCalendar today = new GregorianCalendar ( );
    int day = today.get ( today.DAY_OF_MONTH );
    int pos = day * 10 - 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_result);

        readWordData();

        if(day == 0) {
            pos = 0;
        }

        ListView list = (ListView)findViewById(R.id.listCorrect);

        final ArrayList<String> Items = new ArrayList<String>(); // 빈 데이터 리스트 생성

        for (int idx = 1; idx < 11; idx++) {
                Items.add(words[pos] + "    " + meaning[pos]);
                pos++;
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Items); //어뎁터 생성

        // 리스트뷰 생성 및 어뎁터 지정
        list.setAdapter(adapter);

        // tv.setTextColor(Color.WHITE); >> 색갈변경
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
