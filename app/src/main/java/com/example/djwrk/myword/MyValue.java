package com.example.djwrk.myword;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import java.util.ArrayList;

public class MyValue {
    public static String value = "";

    public static String saveTempMenu = "";
    public static String saveTempContent = "";


    static public ArrayList<MyDataList> getEng(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("eng", Context.MODE_PRIVATE);
        String result = prefs.getString("data", null);
        ArrayList<MyDataList> temp = stringToMyDataList(result);
        Log.d("qwe", result + "!!!!");
        return temp;
    }

    static public void saveEng(Context ctx, ArrayList<MyDataList> data) {
        SharedPreferences prefs = ctx.getSharedPreferences("eng", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String result = myDataListToString(data);
        if (data.size() == 0)
            editor.putString("data", "");
        else
            editor.putString("data", result);

        editor.commit();

    }

    private static String myDataListToString(ArrayList<MyDataList> temp) {
        String result = "";
        MyDataList myDataList;
        for (int i = 0; i < temp.size(); i++) {
            myDataList = temp.get(i);
            result += myDataList.getDay() + "★" + myDataList.getValue() + "☆";
        }
        return result;
    }

    private static ArrayList<MyDataList> stringToMyDataList(String temp) {

        String value = "";
        String v1 = "";
        ArrayList<MyDataList> result = new ArrayList<>();
        MyDataList myDataList;

        for (int i = 0; i < temp.length(); i++) {
            Log.d("qwe",value);
            if (!String.valueOf('★').equals(String.valueOf(temp.charAt(i)))
                    && !String.valueOf('☆').equals(String.valueOf(temp.charAt(i)))) {
                value += temp.charAt(i);
            } else if (String.valueOf('★').equals(String.valueOf(temp.charAt(i)))) {
                v1 = value;
                value = "";
            } else if (String.valueOf('☆').equals(String.valueOf(temp.charAt(i)))) {
                myDataList = new MyDataList(v1, value);
                result.add(myDataList);
                value = "";
            }
        }

        return result;
    }
}


