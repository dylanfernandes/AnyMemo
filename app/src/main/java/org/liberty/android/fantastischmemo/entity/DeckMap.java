package org.liberty.android.fantastischmemo.entity;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;

public class DeckMap {

    private static DeckMap instance;
    private static HashSet<Tag> tags;

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
        // Find if the deck is already in the decksMap
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

    public HashSet<Tag> getAllTags() {
        if (tags != null)
            return tags;
        HashSet tags = new HashSet<Tag>();
        for (DeckMock deck : decksMap.values()) {
            tags.addAll(deck.getTags());
        }
        return tags;
    }

    public Tag getTagByName(String name) {
        HashSet<Tag> tags = getAllTags();
        for (Tag tag : tags)
            if (tag.getName() == name)
                return tag;
        return null;
    }

    public HashMap<String, DeckMock> filterDecksByTags(HashSet<Tag> tags) {
        HashMap<String, DeckMock> filteredDecks = new HashMap<String, DeckMock>();
        for (String key : decksMap.keySet()) {
            DeckMock deck = decksMap.get(key);
            if (deck.hasSetTag(tags))
                filteredDecks.put(key, deck);
        }
        return filteredDecks;
    }
}
