package org.liberty.android.fantastischmemo.test.db;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.liberty.android.fantastischmemo.dao.AchievementPointDao;
import org.liberty.android.fantastischmemo.dao.DailyPointsDao;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.entity.DailyPoints;
import org.liberty.android.fantastischmemo.test.AbstractExistingBaseDBTest;


import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by Paul on 2018-04-13.
 */

public class DailyPointDaoTest extends AbstractExistingBaseDBTest {

    private DailyPointsDao dpDao;
    private AchievementPointDao achPointsDao;
    private DailyPoints dp;
    private DailyPoints dp2;
    private AchievementPoint a1;
    private AchievementPoint a2;

    @Before
    public void setup() {
        dpDao = centralDbHelper.getDailyPointsDao();
        achPointsDao = centralDbHelper.getAchievementPointDao();
        //set dailyPoints
        dp = new DailyPoints();
        dp.setId(1);

        //set achievement points
        a1 = new AchievementPoint();
        a2 = new AchievementPoint();
        a1.setValue(3);
        a2.setValue(2);
    }

    public void addPointsToDailyPoints() {
      dp = dpDao.createOrReturn();
      a1.setDailyPoints(dp);
      a2.setDailyPoints(dp);

        try {
            achPointsDao.create(a1);
            achPointsDao.create(a2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dpDao.update(dp);
        dpDao.refresh(dp);
    }

    @Test
    public void testAddDailyPoints() throws Exception {
        assertFalse(dpDao.idExists(1));
        dpDao.create(dp);
        assertEquals(true, dpDao.idExists(1));
        assertFalse(dpDao.idExists(3));
    }

    @Test
    public void testExistingDailyPoints() {
        dp = dpDao.createOrReturn();
        dp2 = dpDao.createOrReturn();
        assertEquals(dp.getId(), dp2.getId());
    }

    @Test
    public void testAddPointsToDailyPoints() throws Exception {
        List<AchievementPoint> pointList;
        AchievementPoint newA1;
        AchievementPoint newA2;

        addPointsToDailyPoints();
        pointList = dp.getPoints();

        newA1 = pointList.get(0);
        newA2 = pointList.get(1);

        Assert.assertEquals(a1.getValue(), newA1.getValue());
        Assert.assertEquals(a2.getValue(), newA2.getValue());
        //verify value field wasn't set with default value
        assertFalse(1 ==  newA1.getValue());
        assertFalse(1 ==  newA2.getValue());
    }

    @Test
    public void testGetSumFromDailyPoints() {
        int initialSum;
        addPointsToDailyPoints();
        initialSum = a1.getValue() + a2.getValue();
        //check if sum is updated in dailyPoints object after adding points to it
        assertEquals(initialSum, (int)dp.getSum());
        //make sure sum was incremented
        assertFalse(0 == dp.getSum() );
    }


}
