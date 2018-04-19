package org.liberty.android.fantastischmemo.test.entity;

import org.junit.Test;
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

    //adding achievement points and getting some tests are in DAO tests since they are dependent on ORM
}
