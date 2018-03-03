package org.liberty.android.fantastischmemo.test.entity;

import org.junit.Test;
import org.liberty.android.fantastischmemo.entity.UserStatistics;

import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



/**
 * Created by Paul on 2018-03-02.
 */

public class UserStatisticsTest {

    UserStatistics us = new UserStatistics();


    @Test
    public void testGetSetId() {
        us.setId(1);
        assertEquals(1, (int)us.getId());
    }

    @Test
    public void testGetSetLastLogin() {
        Date today = new Date();
        us.setLastLogin(today);
        assertEquals(today, us.getLastLogin());
    }

    @Test
    public void testGetSetMultiplier() {
        us.setMultiplier(5);
        assertEquals(5, (int)us.getMultiplier());
    }

    @Test
    public void testGetSetStreak() {
        us.setStreak(2);
        assertEquals(2, (int)us.getStreak());
    }

    @Test
    public void testGetSetLongestStreak() {
        us.setLongestStreak(2);
        assertEquals(2, (int)us.getLongestStreak());
    }

    @Test
    public void testGetSetWeeks() {
        us.setWeeks(2);
        assertEquals(2, (int)us.getWeeks());
    }

    @Test
    public void testGetSetMonths() {
        us.setMonths(2);
        assertEquals(2, (int)us.getMonths());
    }

    @Test
    public void testStreakToLongestStreakChange() {
        us.setLongestStreak(5);
        us.setStreak(10);
        us.streakToLongestStreak();
        assertEquals(10, (int)us.getLongestStreak());
        assertEquals(10, (int)us.getStreak());
    }

    @Test
    public void testSterakToLongestStreakNoChange() {
        us.setLongestStreak(10);
        us.setStreak(5);
        us.streakToLongestStreak();
        assertEquals(5, (int)us.getStreak());
        assertEquals(10, (int)us.getLongestStreak());
    }

    @Test
    public void testStreakToWeeks() {
        us.setStreak(0);
        us.setWeeks(2);
        us.streakToWeeks();
        assertEquals(0, (int)us.getWeeks());

        us.setStreak(21);
        us.setWeeks(2);
        us.streakToWeeks();
        assertEquals(3, (int)us.getWeeks());
    }

    @Test
    public void testStreakToMonths() {
        us.setStreak(0);
        us.setMonths(2);
        us.streakToMonths();
        assertEquals(0, (int)us.getMonths());

        us.setStreak(90);
        us.setMonths(2);
        us.streakToMonths();
        assertEquals(3, (int)us.getMonths());
    }

    @Test
    public void testUpdateStreaks() {
        long MILLIS_PER_DAY = 24*60*60*1000L;

        Date today = new Date();
        long todayInMillis = today.getTime();
        long twoDaysAgo = todayInMillis - (2*MILLIS_PER_DAY);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(twoDaysAgo);
        Date lastLogin = calendar.getTime();

        us.setLastLogin(lastLogin);
        us.setStreak(10);
        us.setLongestStreak(10);
        us.setWeeks(10);
        us.setMonths(10);
        us.updateStreaks();
        assertEquals(0, (int)us.getStreak());
        assertEquals(0, (int)us.getLongestStreak());
        assertEquals(0, (int)us.getWeeks());
        assertEquals(0, (int)us.getMonths());

        us.setLastLogin(today);
        us.updateStreaks();
        assertEquals(1, (int)us.getStreak());

    }

    @Test
    public void testCheckStreak() {
        long MILLIS_PER_DAY = 24*60*60*1000L;

        Date today = new Date();
        long todayInMillis = today.getTime();
        long twoDaysAgo = todayInMillis - (2*MILLIS_PER_DAY);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(twoDaysAgo);
        Date lastLogin = calendar.getTime();

        us.setLastLogin(today);
        assertFalse(us.checkStreak(today));

        us.setLastLogin(lastLogin);
        assertTrue(us.checkStreak(today));

    }

}