package com.example.boris.mamafoodjob.Managers;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginChecker {
    private Context context;
    private SharedPreferences preferences;

    public LoginChecker(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("com.example.boris.MyPref", Context.MODE_PRIVATE);
    }

    public void write(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("isLogged", "INIT_OK");
        editor.apply();
    }

    public boolean checkPreference(){

        if (preferences.getString("isLogged", "null").equals("null")){
            return false;
        }else{
            return true;
        }

    }

    public void clearPreference(){
       /* SharedPreferences.Editor editor = preferences.edit();
        editor.putString("isLogged", "null");*/
        preferences.edit().clear().apply();
    }
}
