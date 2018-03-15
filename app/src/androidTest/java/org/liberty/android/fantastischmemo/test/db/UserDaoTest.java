package org.liberty.android.fantastischmemo.test.db;

import android.support.test.filters.SmallTest;

import org.junit.Test;
import org.liberty.android.fantastischmemo.dao.UserDao;
import org.liberty.android.fantastischmemo.entity.User;
import org.liberty.android.fantastischmemo.test.AbstractExistingDBTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Emily on 2018-03-03.
 */

public class UserDaoTest  extends AbstractExistingDBTest {

    @Test
    public void testAddUser() throws Exception {
        UserDao userDao = helper.getUserDao();
        long count = userDao.countOf();
        User testUser = userDao.createOrReturn("testUsername");
        assertEquals(count + 1, userDao.countOf());
        assertEquals(testUser.getUsername(), "testUsername");
    }

    @Test
    public void testRemoveUser() throws Exception {
        UserDao userDao = helper.getUserDao();
        User testUser = userDao.createOrReturn("testUsername");
        int id = testUser.getId();
        userDao.delete(testUser);
        assertFalse(userDao.idExists(id));
    }
}
