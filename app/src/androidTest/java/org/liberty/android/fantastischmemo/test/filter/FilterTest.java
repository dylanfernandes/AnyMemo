package org.liberty.android.fantastischmemo.test.filter;

import android.support.test.filters.SmallTest;

import org.junit.Test;
import org.liberty.android.fantastischmemo.entity.DeckMap;
import org.liberty.android.fantastischmemo.entity.DeckMock;
import org.liberty.android.fantastischmemo.entity.Option;
import org.liberty.android.fantastischmemo.entity.Tag;
import org.liberty.android.fantastischmemo.test.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilterTest extends BaseTest {

    @SmallTest
    @Test
    public void testGetAllTags() throws Exception {
        DeckMap deckMap = DeckMap.getInstance();

        Tag language = new Tag("language");
        Tag french = new Tag("french");
        Tag german = new Tag("german");

        List tags1 = new ArrayList<Tag>();
        tags1.add(language);
        tags1.add(french);
        List tags2 = new ArrayList<Tag>();
        tags2.add(language);
        tags2.add(german);
        final DeckMock deckMock1 = deckMap.findOrCreate(new DeckMock("french.db", "/french.db", tags1));
        final DeckMock deckMock2 = deckMap.findOrCreate(new DeckMock("german.db", "/german.db", tags2));

        HashSet<Tag> tags = new HashSet<>(Arrays.asList(language, french, german));
        assertEquals(tags, deckMap.getAllTags());
    }

    @SmallTest
    @Test
    public void testGetTagByName() throws Exception {
        DeckMap deckMap = DeckMap.getInstance();
        Tag french = new Tag("french");

        List tags = new ArrayList<Tag>();
        tags.add(french);
        final DeckMock deckMock = deckMap.findOrCreate(new DeckMock("french.db", "/french.db", tags));

        deckMap.getAllTags();  // Generate memoized hashset of tags inside deckMap

        assertEquals(french, deckMap.getTagByName("french"));
    }

    @SmallTest
    @Test
    public void testFilterDecksByTags() throws Exception {
        DeckMap deckMap = DeckMap.getInstance();

        Tag language = new Tag("language");
        Tag french = new Tag("french");
        Tag german = new Tag("german");
        Tag biology = new Tag("biology");

        List tags1 = new ArrayList<Tag>();
        tags1.add(language);
        tags1.add(french);
        List tags2 = new ArrayList<Tag>();
        tags2.add(language);
        tags2.add(german);
        List tags3 = new ArrayList<Tag>();
        tags3.add(biology);

        final DeckMock deckMock1 = deckMap.findOrCreate(new DeckMock("french.db", "/french.db", tags1));
        final DeckMock deckMock2 = deckMap.findOrCreate(new DeckMock("german.db", "/german.db", tags2));
        final DeckMock deckMock3 = deckMap.findOrCreate(new DeckMock("neuroscience.db", "/neuroscience.db", tags3));

        HashMap<String, DeckMock> filteredDecks = new HashMap<>();
        filteredDecks.put("/french.db", deckMock1);
        filteredDecks.put("/german.db", deckMock2);
        HashSet<Tag> tagsToFilterBy = new HashSet<>(Arrays.asList(language));

        assertEquals(filteredDecks, deckMap.filterDecksByTags(tagsToFilterBy));
    }

}
