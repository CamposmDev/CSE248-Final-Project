package com.campos.util;

import android.util.Log;

public class Sysout {
    public static void println() {
        Log.println(Log.ASSERT, "0", "\n");
    }

    public static void println(String x) {
        Log.println(Log.ASSERT, "0", x);
    }

    public static void println(int x) {
        Log.println(Log.ASSERT, "0", String.valueOf(x));
    }

    public static void println(boolean x) {
        Log.println(Log.ASSERT, "0", String.valueOf(x));
    }
}
