package org.liberty.android.fantastischmemo.test.db;

import org.junit.Before;
import org.junit.Test;
import org.liberty.android.fantastischmemo.dao.AchievementPointDao;
import org.liberty.android.fantastischmemo.dao.AchievementTagPointsJoinDao;
import org.liberty.android.fantastischmemo.dao.TagPointsDao;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.entity.AchievementTagPointsJoin;
import org.liberty.android.fantastischmemo.entity.TagPoints;
import org.liberty.android.fantastischmemo.test.AbstractExistingBaseDBTest;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dylanfernandes on 2018-04-15.
 */

public class TagPointsDaoTest extends AbstractExistingBaseDBTest {
    private TagPointsDao tpDao;
    private AchievementPointDao achPointsDao;
    private AchievementTagPointsJoinDao joinDao;
    private AchievementTagPointsJoin join;
    private AchievementTagPointsJoin join2;
    private AchievementTagPointsJoin join3;
    private TagPoints tp;
    private TagPoints tp1;
    private AchievementPoint a1;
    private AchievementPoint a2;

    @Before
    public void setup() {
        tpDao = centralDbHelper.getTagPointsDao();
        achPointsDao = centralDbHelper.getAchievementPointDao();
        joinDao = centralDbHelper.getAchievementTagPointsJoinDao();

        //create join entity
        join = new AchievementTagPointsJoin();
        join2 = new AchievementTagPointsJoin();
        join3 = new AchievementTagPointsJoin();
        //set deckPoints
        tp = new TagPoints();
        tp.setId(1);
        tp.setTagName("pie");
        tp1 = new TagPoints();
        tp1.setId(2);
        tp1.setTagName("bacon");
        //set achievement points
        a1 = new AchievementPoint();
        a2 = new AchievementPoint();
        a1.setValue(1);
        a2.setValue(2);
    }

    public void joinPointsToTagPoints() {
        tpDao.create(tp);
        tpDao.create(tp1);

        try {
            achPointsDao.create(a1);
            achPointsDao.create(a2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        join.setJoin(a1,tp);

        join2.setJoin(a1,tp1);

        join3.setJoin(a2,tp);


        try {
            joinDao.create(join);
            joinDao.create(join2);
            joinDao.create(join3);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testTag1() throws Exception {
        joinPointsToTagPoints();
        assertEquals("pie", join.getTagPoints().getTagName());
    }

    @Test
    public void testEqualAchievementPoints() throws Exception {
        joinPointsToTagPoints();
        assertEquals(join.getAchievementPoint().getValue(), join2.getAchievementPoint().getValue());
    }

    @Test
    public void testTag2() throws Exception {
        joinPointsToTagPoints();
        assertEquals("bacon", join2.getTagPoints().getTagName());
    }

    @Test
    public void testEqualTagNames() throws Exception {
        joinPointsToTagPoints();
        assertEquals(join.getTagPoints().getTagName(), join3.getTagPoints().getTagName());
    }

    @Test
    public void testSumOne() throws Exception {
        joinPointsToTagPoints();
        assertEquals(join.getTagPoints().getTagName(), join3.getTagPoints().getTagName());
    }

    @Test
    public void testSum2() throws Exception {
        joinPointsToTagPoints();
        assertEquals(join.getTagPoints().getTagName(), join3.getTagPoints().getTagName());
    }



}
