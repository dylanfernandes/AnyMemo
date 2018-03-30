package org.liberty.android.fantastischmemo.test.db;

import org.junit.Test;
import org.liberty.android.fantastischmemo.dao.DeckDao;
import org.liberty.android.fantastischmemo.entity.Deck;
import org.liberty.android.fantastischmemo.test.AbstractExistingBaseDBTest;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Olivier on 2018-03-27.
 */

public class DeckDaoTest extends AbstractExistingBaseDBTest{

    @Test
    public void testAddDeck(){
        DeckDao deckdao = centralDbHelper.getDeckDao();
        Deck newDeck = deckdao.createOrReturn("testingDeck");
        assertEquals("testingDeck",newDeck.getName());
    }

    @Test
    public void testUpdateRating(){
        DeckDao deckdao = centralDbHelper.getDeckDao();
        Deck newDeck = deckdao.createOrReturn("testingDeck");
        newDeck.setRating(3.0);
        assertEquals((Double)3.0,(Double)newDeck.getRating());
        deckdao.updateRating("testingDeck",5);
        //Re-querry for updated value
        newDeck = deckdao.createOrReturn("testingDeck");
        assertEquals((Double)5.0,newDeck.getRating());
    }
}
