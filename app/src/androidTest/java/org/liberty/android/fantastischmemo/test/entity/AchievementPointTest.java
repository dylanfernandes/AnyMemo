package org.liberty.android.fantastischmemo.test.entity;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.entity.DeckPoints;

import org.liberty.android.fantastischmemo.entity.Tag;
import org.liberty.android.fantastischmemo.entity.TagPoints;
import org.mockito.Mock;


import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by dylanfernandes on 2018-03-02.
 */

public class AchievementPointTest {
    private AchievementPoint p = new AchievementPoint();

    @Test
    public void testSetGetId(){
        p.setId(1);
        assertEquals(1,(int)p.getId());
        assertFalse(-1 == p.getId());
    }
    @Test
    public void testGetSetTime(){
        Date d = new Date();
        p.setTime(d);
        assertEquals(d, p.getTime());
    }

    @Test
    public void testGetSetValue(){
        p.setValue(1);
        assertEquals(1,(int)p.getValue());
        assertFalse(-1 == p.getValue());
    }

    @Test
    public void testGetSetDeckPoints(){
        DeckPoints d = mock(DeckPoints.class);
        String deckName = "Test";
        when(d.getDeckName()).thenReturn(deckName);
        p.setDeckPoints(d);
        assertEquals(deckName,p.getDeckPoints().getDeckName());
        verify(d).getDeckName();
    }

    @Test
    public void testGetSetTagPoints(){
        TagPoints tag = mock(TagPoints.class);
        String tagName = "Test";
        when(tag.getTagName()).thenReturn(tagName);
        p.setTagPoints(tag);
        assertEquals(tagName,p.getTagPoints().getTagName());
        verify(tag).getTagName();
    }

}
