package com.example.assessmentapphpl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.assessmentapphpl.databinding.ActivityMainBinding;
import com.example.assessmentapphpl.databinding.OtpVerificationLayoutBinding;
import com.example.assessmentapphpl.helper.Constants;
import com.example.assessmentapphpl.helper.ProgressDialogUtil;
import com.example.assessmentapphpl.helper.Utils;
import com.example.assessmentapphpl.helper.ValidationUtil;
import com.example.assessmentapphpl.view.RegistrationActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;
    String verificationID;
    String phoneNumber;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks =
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                        ProgressDialogUtil.dismissProgressDialog();
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {

                        verificationID = verificationId;
                        phoneNumber = binding.phoneNumberET.getText().toString().trim();
                        ProgressDialogUtil.dismissProgressDialog();
                        inflateOTPInputDialog(verificationId, phoneNumber);
                    }
                };

        binding.reqOtpBtn.setOnClickListener(view -> {

            if (!ValidationUtil.isValidPhoneNumber(binding.phoneNumberET.getText().toString().trim())) {
                Utils.toast(this, "Please enter valid phone number");
                return;
            }

            ProgressDialogUtil.showProgressDialog(this, "Please wait...");

            PhoneAuthOptions options =
                    PhoneAuthOptions.newBuilder(auth)
                            .setPhoneNumber("+88" + binding.phoneNumberET.getText().toString().trim())
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(MainActivity.this)
                            .setCallbacks(callbacks)
                            .build();

            PhoneAuthProvider.verifyPhoneNumber(options);
        });

    }

    private void inflateOTPInputDialog(String verificationId, String phoneNumber) {
        OtpVerificationLayoutBinding verifyOTPBinding = OtpVerificationLayoutBinding.inflate(LayoutInflater.from(this), null, false);

        verifyOTPBinding.otpSentText.setText(String.format("Please type the verification code sent to %s", phoneNumber));


        verifyOTPBinding.verifyOtpBtn.setOnClickListener(view -> {

            if (!ValidationUtil.isValidOTP(verifyOTPBinding.otpInputET.getText().toString().trim())) {
                Utils.toast(this, "Please enter valid OTP");
                return;
            }


            ProgressDialogUtil.showProgressDialog(this, "Verifying...");
            PhoneAuthCredential credential =
                    PhoneAuthProvider.getCredential(verificationId, verifyOTPBinding.otpInputET.getText().toString().trim());

            signInWithCredential(credential);
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertDialog = builder.setView(verifyOTPBinding.getRoot()).create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        alertDialog.show();
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {

                    ProgressDialogUtil.dismissProgressDialog();

                    if (task.isSuccessful()) {
                        if (alertDialog != null && alertDialog.isShowing())
                            alertDialog.dismiss();
                        Utils.toast(this, "Successfully Verified");
                        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                        intent.putExtra(Constants.PASS_USER_PHONE_NUMBER, phoneNumber);
                        startActivity(intent);
                    } else {
                        Utils.toast(this, Objects.requireNonNull(task.getException()).getLocalizedMessage());
                    }
                });
    }

}