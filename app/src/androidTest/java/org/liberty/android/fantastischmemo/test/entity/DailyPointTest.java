package org.liberty.android.fantastischmemo.test.entity;

import org.junit.Test;

import org.liberty.android.fantastischmemo.entity.DailyPoints;
import org.liberty.android.fantastischmemo.utils.DayDateUtil;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Paul on 2018-04-13.
 */

public class DailyPointTest {

    DailyPoints dp = new DailyPoints();

    @Test
    public void setGetTest() {
        dp.setId(5);
        assertEquals(5, (int)dp.getId());
    }

    @Test
    public void setGetTimeTest() {
        Date now = DayDateUtil.getDayDate();
        assertEquals(now, dp.getTime());
    }



}
