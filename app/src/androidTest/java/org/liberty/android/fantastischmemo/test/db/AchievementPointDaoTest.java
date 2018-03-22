package org.liberty.android.fantastischmemo.test.db;

import org.junit.Test;
import org.liberty.android.fantastischmemo.dao.AchievementPointDao;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.test.AbstractExistingBaseDBTest;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dylanfernandes on 2018-03-15.
 */

public class AchievementPointDaoTest extends AbstractExistingBaseDBTest {

    @Test
    public void testAddPoint() throws Exception {
        AchievementPointDao apDao = centralDbHelper.getAchievementPointDao();
        List<AchievementPoint> pointsList  =  apDao.queryForAll();
        long startSize = pointsList.size();
        long endList;
        AchievementPoint ap = new AchievementPoint();
        ap.setId(1);
        apDao.create(ap);
        endList = apDao.countOf();
        assertEquals(startSize + 1, endList);
        assertTrue(apDao.idExists(1));
    }

    @Test
    public void testDeletePoint() throws Exception {
        AchievementPointDao apDao = centralDbHelper.getAchievementPointDao();
        long addSize;
        AchievementPoint ap = new AchievementPoint();
        ap.setId(2);
        apDao.create(ap);
        addSize = apDao.countOf();
        apDao.delete(ap);
        assertEquals(addSize - 1, apDao.countOf());
        assertFalse( apDao.idExists(2));
    }

    @Test
    public void testModifyPoint() throws Exception {
        AchievementPointDao apDao = centralDbHelper.getAchievementPointDao();
        AchievementPoint ap = new AchievementPoint();
        ap.setId(1);
        apDao.create(ap);
        assertTrue( apDao.idExists(1));
        ap.setId(2);
        apDao.createOrUpdate(ap);
        assertTrue( apDao.idExists(2));
    }
}
