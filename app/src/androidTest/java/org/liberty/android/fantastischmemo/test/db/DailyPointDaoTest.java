package org.liberty.android.fantastischmemo.test.db;

import org.junit.Test;
import org.liberty.android.fantastischmemo.dao.DailyPointsDao;
import org.liberty.android.fantastischmemo.entity.DailyPoints;
import org.liberty.android.fantastischmemo.test.AbstractExistingBaseDBTest;


import static junit.framework.Assert.assertTrue;

/**
 * Created by Paul on 2018-04-13.
 */

public class DailyPointDaoTest extends AbstractExistingBaseDBTest {

    @Test
    public void pointsTest() throws Exception {
        DailyPointsDao dailyPointsDao = centralDbHelper.getDailyPointsDao();


        DailyPoints dp = new DailyPoints();
        dp.setId(1);
        dp.setTotalPoints(123);


        dailyPointsDao.create(dp);

        assertTrue(dailyPointsDao.idExists(1));
    }
}
