package org.liberty.android.fantastischmemo.test.entity;

import org.junit.Test;
import org.liberty.android.fantastischmemo.entity.TagPoints;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dylanfernandes on 2018-04-15.
 */

public class TagPointsTest {

    @Test
    public void testGetSetId(){
        TagPoints tp = new TagPoints();
        int id = 5;
        tp.setId(id);
        assertEquals(id, (int)tp.getId());
    }

    @Test
    public void testGetSetTagName() {
        TagPoints tp = new TagPoints();
        String tn = "tag";
        tp.setTagName(tn);
        assertEquals(tn, tp.getTagName());
    }
}
