package org.liberty.android.fantastischmemo.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.AnyMemoDBOpenHelperManager;
import org.liberty.android.fantastischmemo.dao.DeckDao;
import org.liberty.android.fantastischmemo.dao.TagDao;
import org.liberty.android.fantastischmemo.dao.TagDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2018-02-04.
 */

@DatabaseTable(tableName = "tags", daoClass = TagDaoImpl.class)
public class Tag {
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String name;

    public Tag() {
        //empty constructor for ormlite
    }

    public Tag(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(name)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag))
            return false;
        if (obj == this)
            return true;
        if (this.getName().equals(((Tag) obj).getName()))
            return true;
        else
            return false;
    }

    public List<Deck> getDecks() {
        DeckDao deckDao = AnyMemoBaseDBOpenHelperManager.getHelper("central.db").getDeckDao();
        List<Deck> allDecks = null;
        List<Deck> decksToReturn = new ArrayList<>();

        try {
            allDecks = deckDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (allDecks != null) {
            for (Deck deck : allDecks) {
                TagDao tagDao = AnyMemoDBOpenHelperManager.getHelper(deck.getDbPath()).getTagDao();
                List<Tag> tags = null;

                try {
                    tags = tagDao.queryForAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if (tags != null && tags.contains(this)) {
                    decksToReturn.add(deck);
                }
            }
        }

        return decksToReturn;
    }
}
