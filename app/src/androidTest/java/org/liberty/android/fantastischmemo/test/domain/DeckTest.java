package org.liberty.android.fantastischmemo.test.domain;

import org.junit.Test;
import org.liberty.android.fantastischmemo.entity.Deck;

import java.util.Date;
import static org.junit.Assert.assertEquals;


/**
 * Created by Paul on 2018-03-07.
 */

public class DeckTest {

    Deck deck = new Deck();

    @Test
    public void testGetSetId() {
        deck.setId(1);
        assertEquals(1, (int)deck.getId());

    }

    @Test
    public void testGetSetName() {
        deck.setName("english");
        assertEquals("english", deck.getName());

    }

    @Test
    public void testGetSetDescription() {
        deck.setDescription("cards");
        assertEquals("cards", deck.getDescription());

    }

    @Test
    public void testGetSetCreationDate() {
        Date date = new Date();
        deck.setCreationDate(date);
        assertEquals(date, deck.getCreationDate());

    }

    @Test
    public void testGetSetUpdateDate() {
        Date date = new Date();
        deck.setUpdateDate(date);
        assertEquals(date, deck.getUpdateDate());

    }

    @Test
    public void testGetSetDbPath() {
        deck.setDbPath("this/is/the/path.db");
        assertEquals("this/is/the/path.db", deck.getDbPath());

    }

    @Test
    public void testGetSetRating(){
        deck.setRating(3.0);
        assertEquals((Double)3.0,(Double)(deck.getRating()));
    }


}
