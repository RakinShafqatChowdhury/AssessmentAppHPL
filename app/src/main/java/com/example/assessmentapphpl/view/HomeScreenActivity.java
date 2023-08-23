package com.example.assessmentapphpl.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.assessmentapphpl.R;
import com.example.assessmentapphpl.databinding.ActivityHomeScreenBinding;
import com.example.assessmentapphpl.helper.SharedPref;
import com.example.assessmentapphpl.helper.Utils;
import com.example.assessmentapphpl.model.UserRegistrationModel;

public class HomeScreenActivity extends AppCompatActivity {

    private ActivityHomeScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen);

        UserRegistrationModel userRegistrationModel = SharedPref.getInstance(this).getUserInfo();

        binding.setUserInfoModel(userRegistrationModel);

        binding.createNewBtn.setOnClickListener(view -> gotoTaskEntryPage());
        binding.historyBtn.setOnClickListener(view -> gotoTaskDataPage());

    }

    private void gotoTaskDataPage() {

    }

    private void gotoTaskEntryPage() {

    }
}