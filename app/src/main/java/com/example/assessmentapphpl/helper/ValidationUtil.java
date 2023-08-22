package com.example.assessmentapphpl.helper;

import android.widget.EditText;

public class ValidationUtil {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String OTP_PATTERN = "^[0-9]{6,}$";

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        return email.matches(EMAIL_PATTERN);
    }

    public static boolean isValidOTP(String otp) {
        if (otp == null || otp.isEmpty()) {
            return false;
        }

        return otp.matches(OTP_PATTERN);
    }

    public static boolean isValidField(EditText editText) {
        return editText != null && !editText.getText().toString().trim().isEmpty();
    }
}
