package org.liberty.android.fantastischmemo.test.db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.liberty.android.fantastischmemo.dao.AchievementPointDao;
import org.liberty.android.fantastischmemo.dao.TagPointsDao;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.entity.TagPoints;
import org.liberty.android.fantastischmemo.test.AbstractExistingBaseDBTest;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dylanfernandes on 2018-04-15.
 */

public class TagPointsDaoTest extends AbstractExistingBaseDBTest {
    private TagPointsDao tpDao;
    private AchievementPointDao achPointsDao;
    private TagPoints tp;
    private AchievementPoint a1;
    private AchievementPoint a2;

    @Before
    public void setup() {
        tpDao = centralDbHelper.getTagPointsDao();
        achPointsDao = centralDbHelper.getAchievementPointDao();
        //set deckPoints
        tp = new TagPoints();
        tp.setId(1);
        //set achievement points
        a1 = new AchievementPoint();
        a2 = new AchievementPoint();
        a1.setValue(1);
        a2.setValue(2);
    }

    public void addPointsToTagPoints() {
        tpDao.create(tp);

        a1.setTagPoints(tp);
        a2.setTagPoints(tp);
        try {
            achPointsDao.create(a1);
            achPointsDao.create(a2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tpDao.update(tp);
        tpDao.refresh(tp);
    }

    @Test
    public void testAddDTagPoints() throws Exception {
        tpDao.create(tp);
        assertEquals(true, tpDao.idExists(1));
    }

    @Test
    public void testAddPointsToTagPoints() throws Exception {
        List<AchievementPoint> pointList;
        AchievementPoint newA1;
        AchievementPoint newA2;

        addPointsToTagPoints();
        pointList = tp.getPoints();

        newA1 = pointList.get(0);
        newA2 = pointList.get(1);

        Assert.assertEquals(a1.getValue(), newA1.getValue());
        Assert.assertEquals(a2.getValue(), newA2.getValue());
    }

    @Test
    public void testGetSumFromTagPoints() {
        int initialSum;
        addPointsToTagPoints();
        initialSum = a1.getValue() + a2.getValue();
        //check if sum is updated in TagPoints object after adding points to it
        assertEquals(initialSum, (int)tp.getSum());
    }
}
