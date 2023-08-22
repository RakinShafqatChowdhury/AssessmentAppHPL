package com.example.assessmentapphpl.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.assessmentapphpl.R;
import com.example.assessmentapphpl.databinding.ActivityRegistrationBinding;
import com.example.assessmentapphpl.helper.Constants;
import com.example.assessmentapphpl.helper.Utils;
import com.example.assessmentapphpl.helper.ValidationUtil;

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
            binding.contactNumberET.setClickable(false);
            binding.contactNumberET.setEnabled(false);
        }

        binding.submitBtn.setOnClickListener(view -> submitRegistration());
    }

    private void submitRegistration() {
        if (!ValidationUtil.isValidField(binding.userNameET)) {
            Utils.alert(this, "Warning!", "Please enter valid name");
            return;
        }

        if (!ValidationUtil.isValidEmail(binding.userEmailET.getText().toString().trim())) {
            Utils.alert(this, "Warning!", "Please enter valid email");
            return;
        }
    }
}