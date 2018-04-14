package org.liberty.android.fantastischmemo.test.db;

import org.junit.Assert;
import org.junit.Test;
import org.liberty.android.fantastischmemo.dao.AchievementPointDao;
import org.liberty.android.fantastischmemo.dao.DeckPointsDao;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.entity.DeckPoints;
import org.liberty.android.fantastischmemo.test.AbstractExistingBaseDBTest;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dylanfernandes on 2018-04-13.
 */

public class DeckPointsDaoTest extends AbstractExistingBaseDBTest {

    @Test
    public void testAddDeckPoints() throws Exception {
        DeckPointsDao dpDao = centralDbHelper.getDeckPointsDao();
        DeckPoints dp = new DeckPoints();
        dp.setId(1);
        dpDao.create(dp);
        assertEquals(true, dpDao.idExists(1));
    }

    @Test
    public void testAddPointsToDeckPoints() throws Exception {
        DeckPointsDao dpDao = centralDbHelper.getDeckPointsDao();
        DeckPoints dp = new DeckPoints();
        dp.setId(1);
        dpDao.create(dp);

        AchievementPointDao achPointsDao = centralDbHelper.getAchievementPointDao();

        AchievementPoint a1 = new AchievementPoint();
        AchievementPoint a2 = new AchievementPoint();
        a1.setValue(1);
        a2.setValue(2);
        a1.setDeckPoints(dp);
        a2.setDeckPoints(dp);
        try {
            achPointsDao.create(a1);
            achPointsDao.create(a2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //dp.addPoint(a1);
        //dp.addPoint(a2);
        dpDao.update(dp);
        dpDao.refresh(dp);

        List<AchievementPoint> pointList = dp.getPoints();

        AchievementPoint newA1 = pointList.get(0);
        AchievementPoint newA2 = pointList.get(1);

        Assert.assertEquals(newA1.getValue(), a1.getValue());
        Assert.assertEquals(newA2.getValue(), a2.getValue());
    }
}
