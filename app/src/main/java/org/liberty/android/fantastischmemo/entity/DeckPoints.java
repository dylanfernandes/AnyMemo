package org.liberty.android.fantastischmemo.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.DeckPointsDaoImpl;

/**
 * Created by dylanfernandes on 2018-04-12.
 */

@DatabaseTable(tableName = "deckpoints", daoClass = DeckPointsDaoImpl.class)
public class DeckPoints extends PointGrouping {

    @DatabaseField(defaultValue = "", width = 8192)
    private String deckName;


    public DeckPoints() {
    }

    public String getDeckName() { return deckName; }


    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }


}
