package com.example.assessmentapphpl.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.assessmentapphpl.R;
import com.example.assessmentapphpl.helper.SharedPref;
import com.example.assessmentapphpl.helper.Utils;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Utils.alert(this, SharedPref.getInstance(this).getUserInfo().getUserName(),
                SharedPref.getInstance(this).getUserInfo().getUserEmail()+" "+SharedPref.getInstance(this).getUserInfo().getUserPhoneNumber());
    }
}