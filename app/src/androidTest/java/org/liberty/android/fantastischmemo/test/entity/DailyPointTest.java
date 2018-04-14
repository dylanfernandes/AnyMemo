package org.liberty.android.fantastischmemo.test.entity;

import org.junit.Test;

import org.liberty.android.fantastischmemo.entity.DailyPoints;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Paul on 2018-04-13.
 */

public class DailyPointTest {

    DailyPoints dp = new DailyPoints();
    AnyMemoBaseDBOpenHelperManager dbManager;
    AnyMemoBaseDBOpenHelper baseHelper;

    @Test
    public void setGetTest() {
        dp.setId(5);
        assertEquals(5, (int)dp.getId());
    }

    @Test
    public void setGetTimeTest() {
        Date now = new Date();
        dp.setTime(now);
        assertEquals(now, dp.getTime());
    }

    @Test
    public void setGetTotalPoints() {
        dp.setTotalPoints(50);
        assertEquals(51, (int)dp.getTotalPoints()+1);
    }


}
