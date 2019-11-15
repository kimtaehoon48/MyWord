package com.example.djwrk.myword;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onClick(View view)
    {
        switch(view.getId()) {
            case R.id.myWord:
                Intent intent1 = new Intent(MainActivity.this, MyWord.class);
                startActivity(intent1);
                break;
            case R.id.wordList:
                Intent intent2 = new Intent(MainActivity.this, WordList.class);
                startActivity(intent2);
                break;
        }
    }

}


