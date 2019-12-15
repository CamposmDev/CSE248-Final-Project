package com.campos.util;

import android.app.AlertDialog;
import android.content.Context;

public class AlertHelper {

    public static void showMessage(Context context, String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("Thank You!", null);
        alert.show();
    }
}
