package com.example.assessmentapphpl.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.assessmentapphpl.R;
import com.example.assessmentapphpl.databinding.ActivityRegistrationBinding;
import com.example.assessmentapphpl.helper.Constants;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding binding;
    String userPhoneNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);

        if (getIntent().getExtras() != null) {
            userPhoneNumber = getIntent().getStringExtra(Constants.PASS_USER_PHONE_NUMBER);
            binding.contactNumberET.setText(userPhoneNumber);
        }
    }
}