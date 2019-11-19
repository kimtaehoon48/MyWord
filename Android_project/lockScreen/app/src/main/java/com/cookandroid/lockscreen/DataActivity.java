package com.cookandroid.lockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DataActivity extends Activity {

    static String[] words = new String[200];
    static String[] meaning = new String[200];

    TextView tc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_test);

        readWordData();

        tc = (TextView)findViewById(R.id.sa);
        tc.setText(words[0]);

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
