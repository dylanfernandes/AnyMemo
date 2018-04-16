package org.liberty.android.fantastischmemo.utils;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Paul on 2018-04-15.
 */

public class DayDateUtil {

    public static String getDayDateString() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());
        cal.set(currentTime.get(Calendar.YEAR), currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH));
        Date time = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(time);

    }
}
