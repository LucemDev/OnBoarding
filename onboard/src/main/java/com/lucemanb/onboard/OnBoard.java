package com.lucemanb.onboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

public class OnBoard {

    public static View[] layouts;
    public static int colorActive;
    public static int colorInactive;
    public static String nextText = "Next";
    public static String startText = "Start";
    public static Class aClass;

    public void setNextText(String nextText) {
        OnBoard.nextText = nextText;
    }

    public void setStartText(String startText) {
        OnBoard.startText = startText;
    }

    public void setFinalActivity(Class aClass) {
        OnBoard.aClass = aClass;
    }

    public OnBoard(View[] layouts,int colorActive,int colorInactive, Class aClass) {
        OnBoard.layouts = layouts;
        OnBoard.colorActive = colorActive;
        OnBoard.colorInactive = colorInactive;
        OnBoard.aClass = aClass;
    }

    public void startOnBoard(Activity activity){
        activity.startActivity(new Intent(activity, OnBoarding.class));
    }

    public static View getSampleLayout(Context context){
        return LayoutInflater.from(context).inflate(R.layout.onboard_sample_1, null);
    }

}
