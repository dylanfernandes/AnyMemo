package org.liberty.android.fantastischmemo.test.db;


import org.liberty.android.fantastischmemo.test.AbstractExistingDBTest;

import org.liberty.android.fantastischmemo.entity.UserStatistics;
import org.liberty.android.fantastischmemo.entity.User;
import org.liberty.android.fantastischmemo.dao.UserStatisticsDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * Created by Paul on 2018-03-14.
 */

public class UserStatisticsDaoTest extends AbstractExistingDBTest {

    private UserStatisticsDao userStatsDao = helper.getUserStatisticsDao();

    @Test
    public void testAddUserStatistics() throws Exception {
        long count = userStatsDao.countOf();
        User user = new User();
        user.setId(1);
        UserStatistics userStats = userStatsDao.createOrReturn(user);
        assertEquals(count + 1, userStatsDao.countOf());
        assertEquals(user.getId(), userStats.getUserId());
    }

    @Test
    public void testDeleteUserStatistics() throws Exception {
        User user = new User();
        user.setId(1);
        UserStatistics userStats = userStatsDao.createOrReturn(user);
        int statsId = userStats.getId();
        userStatsDao.delete(userStats);
        assertFalse(userStatsDao.idExists(statsId));
    }

    @Test
    public void testReturnUserStatistics() throws Exception {
        long count = userStatsDao.countOf();
        User user = new User();
        user.setId(1);
        UserStatistics userStats = userStatsDao.createOrReturn(user);
        int statsId = userStats.getId();
        UserStatistics sameUserStats = userStatsDao.createOrReturn(user);
        int sameStatsId = sameUserStats.getId();
        assertEquals(statsId, sameStatsId);
        assertEquals(count + 1, userStatsDao.countOf());

    }






}
