package com.campos.util;

import java.util.Calendar;
import java.util.Date;

public class MyUtils {
    public static boolean isNewYear() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.MONTH, 11);
        return date.after(cal.getTime());
    }
}
