package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.dao.Dao;

import org.liberty.android.fantastischmemo.entity.Deck;

public interface DeckDao extends Dao<Deck, Integer> {

    Deck createOrReturn(String deckName);
    void updateRating(String deckName, int rating);
}
