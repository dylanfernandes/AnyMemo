package org.liberty.android.fantastischmemo.test.db;

import org.junit.Test;
import org.liberty.android.fantastischmemo.dao.DeckPointsDao;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.entity.DeckPoints;
import org.liberty.android.fantastischmemo.test.AbstractExistingBaseDBTest;
import static junit.framework.Assert.assertEquals;

/**
 * Created by dylanfernandes on 2018-04-13.
 */

public class DeckPointsDaoTest extends AbstractExistingBaseDBTest {

    @Test
    public void testInitialisation() throws Exception {
        DeckPointsDao dpDao = centralDbHelper.getDeckPointsDao();
        DeckPoints dp = new DeckPoints();
        dp.setId(1);
        dpDao.create(dp);
        assertEquals(true, dpDao.idExists(1));
    }
}
