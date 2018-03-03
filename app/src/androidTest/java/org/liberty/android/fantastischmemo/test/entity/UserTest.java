package org.liberty.android.fantastischmemo.test.entity;

import org.junit.Test;
import org.liberty.android.fantastischmemo.entity.User;
import org.liberty.android.fantastischmemo.entity.UserStatistics;

import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;

/**
 * Created by Paul on 2018-03-02.
 */

public class UserTest {

    User user = new User();

    @Test
    public void testGetSetId() {
        user.setId(1);
        assertEquals(1, (int)user.getId());
    }

    @Test
    public void testGetSetSurname() {
        user.setSurname("paul");
        assertEquals("paul", user.getSurname());
    }

    @Test
    public void testGetSetName() {
        user.setName("richard");
        assertEquals("richard", user.getName());
    }

    @Test
    public void testGetSetUsername() {
        user.setUsername("prich");
        assertEquals("prich", user.getSurname());
    }

    @Test
    public void testGetSetUserStatistic() {
        UserStatistics us = new UserStatistics();
        us.setId(5);
        user.setUserStatistics(us);
        assertEquals(5, (int)user.getUserStatistics().getId());
    }

}
