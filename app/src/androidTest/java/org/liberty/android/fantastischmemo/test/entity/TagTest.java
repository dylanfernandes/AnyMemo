package org.liberty.android.fantastischmemo.test.entity;

import org.junit.Before;
import org.junit.Test;
import org.liberty.android.fantastischmemo.entity.Tag;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dylanfernandes on 2018-03-07.
 */

public class TagTest {

    @Test
    public void testGetSetId(){
        Tag t = new Tag("test");
        t.setId(1);
        assertEquals(1, (int)t.getId());
    }

    @Test
    public void testGetSetName(){
        //constructor name initialization
        Tag t = new Tag("initial");
        assertEquals("initial", t.getName());
        //setter initialization
        t.setName("new");
        assertEquals("new", t.getName());

    }

    @Test
    public void testToString(){
        //expected to return value of name
        Tag t = new Tag("string");
        assertEquals("string", t.toString());
    }

    @Test
    public void testEquals(){
        //name equality determines tag instance equality
        Tag t1 = new Tag("test");
        Tag t2 = new Tag("test");
        Tag t3 = new Tag("notTest");
        //true outcome test
        assertTrue(t1.getName() == t2.getName());
        assertTrue(t1 == t2);
        //false outcome test
        assertFalse(t1.getName() == t3.getName());
        assertFalse(t1 == t3);
    }

}
