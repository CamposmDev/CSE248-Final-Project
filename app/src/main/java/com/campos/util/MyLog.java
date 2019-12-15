package com.campos.util;

import android.util.Log;

public class MyLog {
    public static void println(String x) {
        Log.println(Log.ASSERT, "0", x);
    }

    public static void println(int x) {
        Log.println(Log.ASSERT, "0", String.valueOf(x));
    }


}
