package org.liberty.android.fantastischmemo.utils;


import java.util.Calendar;
import java.util.Date;

/**
 * Created by Paul on 2018-04-15.
 */

public class DayDateUtil {

    public static Date getDayDate() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
        Date time = cal.getTime();
        return time;

    }
}
