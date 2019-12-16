package com.campos.util;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

public class AlertUtil {

    public static void showMessage(Context context, String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("Ok", null);
        alert.show();
    }

    public static void showToast(Context context, String message, int length) {
        Toast.makeText(context, message, length).show();
    }
}
