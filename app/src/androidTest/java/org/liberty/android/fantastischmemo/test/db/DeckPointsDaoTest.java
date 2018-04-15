package org.liberty.android.fantastischmemo.test.db;

import org.junit.Assert;
import org.junit.Before;
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
    private DeckPointsDao dpDao;
    private AchievementPointDao achPointsDao;
    private DeckPoints dp;
    private AchievementPoint a1;
    private AchievementPoint a2;

    @Before
    public void setup() {
        dpDao = centralDbHelper.getDeckPointsDao();
        achPointsDao = centralDbHelper.getAchievementPointDao();
        //set deckPoints
        dp = new DeckPoints();
        dp.setId(1);
        //set achievement points
        a1 = new AchievementPoint();
        a2 = new AchievementPoint();
        a1.setValue(1);
        a2.setValue(2);
    }

    //setup relationship between points and deckPoints in database
    public void addPointsToDeckPoints() {
        dpDao.create(dp);

        a1.setDeckPoints(dp);
        a2.setDeckPoints(dp);
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
    public void testAddDeckPoints() throws Exception {
        dpDao.create(dp);
        assertEquals(true, dpDao.idExists(1));
    }

    //Adding achievement points to deck points dependent on ORM in other to have right relationship in DB
    @Test
    public void testAddPointsToDeckPoints() throws Exception {
        List<AchievementPoint> pointList;
        AchievementPoint newA1;
        AchievementPoint newA2;

        addPointsToDeckPoints();
        pointList = dp.getPoints();

        newA1 = pointList.get(0);
        newA2 = pointList.get(1);

        Assert.assertEquals(a1.getValue(), newA1.getValue());
        Assert.assertEquals(a2.getValue(), newA2.getValue());
    }

    //Getting Sum dependent on ORM in other to have right relationship in DB
    @Test
    public void testGetSumFromDeckPoints() {
        int initialSum;
        addPointsToDeckPoints();
        initialSum = a1.getValue() + a2.getValue();
        //check if sum is updated in deckPoints object after adding points to it
        assertEquals(initialSum, (int)dp.getSum());
    }
}
