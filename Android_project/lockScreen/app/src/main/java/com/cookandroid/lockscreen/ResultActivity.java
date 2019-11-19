package com.cookandroid.lockscreen;

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
import java.util.List;

public class ResultActivity extends Activity {

    static String[] words = new String[200];
    static String[] meaning = new String[200];

    //boolean[] isCorrect = new boolean[11];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_study);

        readWordData();

        Intent intent = getIntent();
        boolean isCorrect[] = intent.getBooleanArrayExtra("correct");
        int pos = intent.getIntExtra("pos", 0);
        pos = pos -2;


        ListView listCorrect = (ListView)findViewById(R.id.listCorrect);
        ListView listWorng = (ListView)findViewById(R.id.listWrong);

        final ArrayList<String> correctItems = new ArrayList<String>(); // 빈 데이터 리스트 생성
        final ArrayList<String> worngItems = new ArrayList<String>(); // 빈 데이터 리스트 생성

        for (int idx = 1; idx < 11; idx++) {
            if (isCorrect[idx]) {
                correctItems.add(words[pos] + "    " + meaning[pos]);
            } else {
                worngItems.add(words[pos] + "    " + meaning[pos]);
            }
            pos++;
        }

        final ArrayAdapter adapterCor = new ArrayAdapter(this, android.R.layout.simple_list_item_1, correctItems); //어뎁터 생성
        final ArrayAdapter adapterWor = new ArrayAdapter(this, android.R.layout.simple_list_item_1, worngItems); //어뎁터 생성

        // 리스트뷰 생성 및 어뎁터 지정
        listCorrect.setAdapter(adapterCor);
        listWorng.setAdapter(adapterWor);

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

    private List<WordSample> wordSamples = new ArrayList<>();
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
