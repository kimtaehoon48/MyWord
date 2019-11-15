package com.example.djwrk.myword;

public class MyDataList {

    private String Day;
    private String Value;

    MyDataList(String v1, String v2){
        Day = v1;
        Value = v2;
    }

    public String getDay()
    {
        return Day;
    }

    public String getValue() {
        return Value;
    }
}
