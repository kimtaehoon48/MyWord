package com.example.djwrk.myword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;





public class CustomAdapter extends BaseAdapter {

    ArrayList<MyDataList> Day;
    Context context;
    ListView temp;
    CustomAdapter me;
    Iterator<String> keyFinder;

    CustomAdapter(Context context,ArrayList<MyDataList> Day,ListView temp )
    {
        this.Day=Day;
        this.context = context;
        this.temp = temp;
        me = this;
    }
    public void add(MyDataList data)
    {
        Day.add(data);
    }


    public void removeItem(int position)
    {
        Day.remove(position);
    }

    @Override
    public int getCount() {
        return Day.size();
    }

    @Override
    public Object getItem(int position) {
        return Day.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            final MyDataList t = (MyDataList) getItem(position);

            Button btn = (Button)convertView.findViewById(R.id.delete);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"저장하기를 누르면 완전히 삭제됩니다.",Toast.LENGTH_SHORT).show();
                    removeItem(position);
                    notifyDataSetChanged();
                    temp.setAdapter(me);


                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyValue.value = t.getValue();
                    Toast.makeText(context,"갱신합니다.",Toast.LENGTH_LONG).show();
                }
            });
        }
        else
            convertView.setTag(position);

        return convertView;
    }


}
