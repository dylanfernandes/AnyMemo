package org.liberty.android.fantastischmemo.test.entity;

import org.junit.Ignore;
import org.junit.Test;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.entity.DeckPoints;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by dylanfernandes on 2018-04-12.
 */

public class DeckPointsTest {
    @Test
    public void testGetSetId(){
        DeckPoints dp = new DeckPoints();
        int id = 5;
        dp.setId(id);
        assertEquals(id, (int)dp.getId());
    }

    @Test
    public void testGetSetDeckName() {
        DeckPoints dp = new DeckPoints();
        String dn = "deck";
        dp.setDeckName(dn);
        assertEquals(dn, dp.getDeckName());
    }

    @Ignore
    @Test
    public void testGetSetPoint() {
        DeckPoints dp = new DeckPoints();
        AchievementPoint point = new AchievementPoint();
        point.setValue(5);
        dp.addPoint(point);
        assertEquals(point.getValue(), dp.getPoints().get(0).getValue());
    }

    @Ignore
    @Test
    public void testGetSetSum() {
        DeckPoints dp = new DeckPoints();
        //test intialization of sum
        assertEquals(0, (int)dp.getSum());
        AchievementPoint point = new AchievementPoint();
        point.setValue(5);
        dp.addPoint(point);
        //verify if sum is updated if a point is added to the object
        assertEquals(point.getValue(), dp.getSum());
    }
}
