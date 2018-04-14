package org.liberty.android.fantastischmemo.test.entity;

import org.junit.Test;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;


import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by dylanfernandes on 2018-03-02.
 */

public class AchievementPointTest {
    private AchievementPoint p = new AchievementPoint();

    @Test
    public void testSetGetId(){
        p.setId(1);
        assertEquals(1,(int)p.getId());
    }
    @Test
    public void testGetSetTime(){
        Date d = new Date();
        p.setTime(d);
        assertEquals(d, p.getTime());
    }

    @Test
    public void testGetSetValue(){
        p.setValue(1);
        assertEquals(1,(int)p.getValue());
    }



}
