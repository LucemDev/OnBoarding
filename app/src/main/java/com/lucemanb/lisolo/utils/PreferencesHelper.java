package com.lucemanb.lisolo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private SharedPreferences preferences;

    private String LAUNCH_CHECK = "launch";

    private boolean getBoolean(String key){return preferences.getBoolean(key, true);}
    private void setBoolean(String key, boolean value){preferences.edit().putBoolean(key, value).apply();}

    public PreferencesHelper(Context context){
        preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
    }

    public boolean isFirstTimeLaunch(){
        return getBoolean(LAUNCH_CHECK);
    }
    public void setIsFirstTimeLaunch(boolean value){
        setBoolean(LAUNCH_CHECK, value);
    }
}
