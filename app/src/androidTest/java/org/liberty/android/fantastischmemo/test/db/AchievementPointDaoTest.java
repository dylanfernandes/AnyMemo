package org.liberty.android.fantastischmemo.test.db;

import org.junit.Test;
import org.liberty.android.fantastischmemo.dao.AchievementPointDao;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.test.AbstractExistingDBTest;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dylanfernandes on 2018-03-15.
 */

public class AchievementPointDaoTest extends AbstractExistingDBTest {

    @Test
    public void testAddPoint() throws Exception{
        AchievementPointDao apDao = helper.getAchievementPointDao();
        List<AchievementPoint> pointsList  = new ArrayList<>();//=  apDao.queryForAll();
        int startSize = pointsList.size();
        int endList;
        AchievementPoint ap = new AchievementPoint();
        ap.setValue(5);
        apDao.create(ap);
        pointsList = apDao.queryForAll();
        endList = pointsList.size();
        assertEquals(startSize + 1, endList);
    }
}
