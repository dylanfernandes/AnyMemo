package org.liberty.android.fantastischmemo.test.filter;

import android.support.test.filters.SmallTest;
import android.util.Log;

import org.junit.Test;
import org.liberty.android.fantastischmemo.entity.DeckMap;
import org.liberty.android.fantastischmemo.entity.DeckMock;
import org.liberty.android.fantastischmemo.entity.Tag;
import org.liberty.android.fantastischmemo.test.BaseTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FilterTest extends BaseTest {

    private DeckMap deckMap;

    private Tag languageTag;
    private Tag spanishTag;
    private Tag germanTag;
    private Tag biologyTag;

    private HashSet<Tag> allTags = new HashSet<>();
    private HashSet<Tag> spanishTags = new HashSet<>();
    private HashSet<Tag> germanTags = new HashSet<>();
    private HashSet<Tag> neuroscienceTags = new HashSet<>();

    private DeckMock spanishDeck;
    private DeckMock germanDeck;
    private DeckMock neuroscienceDeck;

    private void initializeSingleton() {

        DeckMap.resetState();
        deckMap = DeckMap.getInstance();

        languageTag = new Tag("language");
        spanishTag = new Tag("spanish");
        germanTag = new Tag("german");
        biologyTag = new Tag("biology");

        allTags.add(languageTag);
        allTags.add(spanishTag);
        allTags.add(germanTag);
        allTags.add(biologyTag);

        spanishTags.add(languageTag);
        spanishTags.add(spanishTag);
        
        germanTags.add(languageTag);
        germanTags.add(germanTag);

        neuroscienceTags.add(biologyTag);

        // Get added to DeckMap implicitly
        List<Tag> spanishTagList = new ArrayList<>();
        spanishTagList.add(languageTag);
        spanishTagList.add(spanishTag);
        spanishDeck = new DeckMock("spanish.db", "/spanish.db", spanishTagList);
        List<Tag> germanTagList = new ArrayList<>();
        spanishTagList.add(languageTag);
        spanishTagList.add(germanTag);
        germanDeck = new DeckMock("german.db", "/german.db", germanTagList);
        List<Tag> neuroscienceTagList = new ArrayList<>();
        neuroscienceTagList.add(biologyTag);
        neuroscienceDeck = new DeckMock("neuroscience.db", "/neuroscience.db", neuroscienceTagList);
    }

    @SmallTest
    @Test
    public void testGetAllTags() throws Exception {
        initializeSingleton();
        assertEquals(allTags, deckMap.getAllTags());
        deckMap.resetState();  // Performed because Singletons are uncooperative
                               // and thus very hard to test without performing a reset
    }

    @SmallTest
    @Test
    public void testGetTagByName() throws Exception {
        initializeSingleton();

        List<Tag> spanishTagList = new ArrayList<>();
        spanishTagList.add(languageTag);
        spanishTagList.add(spanishTag);
        final DeckMock spanishDeck = deckMap.findOrCreate(new DeckMock("spanish.db", "/spanish.db", spanishTagList));

        deckMap.getAllTags();  // Generate memoized hashset of tags inside deckMap

        assertEquals(new Tag("spanish"), deckMap.getTagByName("spanish"));

        deckMap.resetState();  // Performed because Singletons are uncooperative
                               // and thus very hard to test without performing a reset
    }

    @SmallTest
    @Test
    public void testFilterDecksByTags() throws Exception {
        initializeSingleton();

        HashMap<String, DeckMock> expectedfilteredDecks = new HashMap<>();
        expectedfilteredDecks.put("/spanish.db", spanishDeck);
        expectedfilteredDecks.put("/german.db", germanDeck);

        HashSet<Tag> tagsToFilterBy = new HashSet<>();
        tagsToFilterBy.add(new Tag("language"));

        HashMap<String, DeckMock> filteredDecks = deckMap.filterDecksByTags(tagsToFilterBy);
        for (DeckMock deck : filteredDecks.values()) {
            assertTrue(containsMatchingDeck(expectedfilteredDecks, deck));
        }

        deckMap.resetState();  // Performed because Singletons are uncooperative
                               // and thus very hard to test without performing a reset
    }

    private boolean containsMatchingDeck(HashMap<String, DeckMock> decks, DeckMock deck) {
        for (DeckMock checkDeck : decks.values()) {
            if (checkDeck.getName().equals(deck.getName())
                    && checkDeck.getDbPath().equals(deck.getDbPath()))
                return true;
        }
        return false;
    }

}
