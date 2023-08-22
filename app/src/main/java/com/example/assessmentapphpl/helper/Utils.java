package com.example.assessmentapphpl.helper;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class Utils {

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void alert(Context context, String title, String msg) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

}
