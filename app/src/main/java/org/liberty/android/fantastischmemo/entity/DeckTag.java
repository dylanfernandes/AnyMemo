package org.liberty.android.fantastischmemo.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.DeckTagDaoImpl;

/**
 * Created by Adam on 2018-02-04.
 */

@DatabaseTable(tableName = "decktags", daoClass = DeckTagDaoImpl.class)
public class DeckTag {
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(foreign = true)
    Deck deck;

    @DatabaseField(foreign = true)
    Tag tag;

    DeckTag() {
        //empty constructor for ormlite
    }

    public DeckTag(Deck deck, Tag tag){
        this.deck = deck;
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
