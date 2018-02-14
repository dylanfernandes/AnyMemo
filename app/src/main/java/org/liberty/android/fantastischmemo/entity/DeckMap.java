package org.liberty.android.fantastischmemo.entity;

import android.util.Log;

import java.util.HashMap;

public class DeckMap {

    private static DeckMap instance;

    public static DeckMap getInstance() {
        if (instance == null) {
            instance = new DeckMap();
        }
        return instance;
    }

    private HashMap<String, DeckMock> decksMap;

    private DeckMap() {
        decksMap = new HashMap<>();
    }

    public HashMap<String, DeckMock> getDecksMap() {
        return decksMap;
    }

    public DeckMock findOrCreate(DeckMock deck) {
        DeckMock deckMock = decksMap.get(deck.getDbPath());

        if (deckMock == null) {
            deckMock = deck;
            decksMap.put(deck.getDbPath(), deckMock);
            Log.d("DeckMap", "Creating new deck");
        } else {
            Log.d("DeckMap", "There is a deck already");
        }

        return deckMock;
    }
}
