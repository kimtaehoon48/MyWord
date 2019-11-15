package com.example.djwrk.myword;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class PagerItem extends PagerAdapter {
    Context context;
    ArrayList<String> word;
    ArrayList<String> mean;

    PagerItem(Context context, ArrayList<String> word,ArrayList<String> mean) {
        this.context = context;
        this.word = word;
        this.mean = mean;
    }

    private String wordColor()
    {
        if(TextStatus.wordStatus==false)
            return "#64829a";
        else
            return "#d5e7f5";
    }
    private String meanColor()
    {
        if(TextStatus.meanStatus == false)
            return "#39709b";
        else
            return "#ffffff";
    }

    @Override
    public int getCount() {
        return word.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_viewpager, container, false);
        final TextView textWord = (TextView)layout.findViewById(R.id.word);
        final TextView textMean = (TextView)layout.findViewById(R.id.mean);
        textWord.setText(word.get(position));
        textMean.setText(mean.get(position));

        Button wordDel = (Button)layout.findViewById(R.id.wordDel);
        wordDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextStatus.wordStatus = !TextStatus.wordStatus;
                textWord.setTextColor(Color.parseColor(wordColor()));
                notifyDataSetChanged();
            }
        });
        Button meanDel = (Button)layout.findViewById(R.id.meanDel);
        meanDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextStatus.meanStatus = !TextStatus.meanStatus;
                textMean.setTextColor(Color.parseColor(meanColor()));
                notifyDataSetChanged();
            }
        });

        textWord.setTextColor(Color.parseColor(wordColor()));
        textMean.setTextColor(Color.parseColor(meanColor()));
        container.addView(layout, 0);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View)object;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
