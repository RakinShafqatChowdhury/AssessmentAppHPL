package com.example.assessmentapphpl.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.assessmentapphpl.model.UserRegistrationModel;
import com.google.gson.Gson;

public class SharedPref {
    private static final String PREF_NAME = "assessmentPrefData";
    public static String USER_INFO = "userInfo";

    private final SharedPreferences sharedPreferences;

    public static SharedPref getInstance(Context context) {
        return new SharedPref(context);
    }

    private SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserInfo(UserRegistrationModel user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_INFO, new Gson().toJson(user));
        editor.apply();

    }

    public UserRegistrationModel getUserInfo() {
        return new Gson().fromJson(sharedPreferences.getString(USER_INFO, null), UserRegistrationModel.class);
    }
}
