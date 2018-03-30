package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import org.liberty.android.fantastischmemo.entity.Deck;

import java.sql.SQLException;

public class DeckDaoImpl extends BaseDaoImpl<Deck, Integer> implements DeckDao {
    public DeckDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Deck> tableConfig)
        throws SQLException {
        super(connectionSource, Deck.class);
    }
    public DeckDaoImpl(ConnectionSource connectionSource, Class<Deck> clazz)
        throws SQLException {
        super(connectionSource, clazz);
    }

    public void updateRating(String deckName, int rating) {
        try {
            UpdateBuilder<Deck, Integer> ub = updateBuilder();
            ub.where().eq("name", deckName);
            ub.updateColumnValue("rating", rating);
            ub.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Deck createOrReturn(String deckName){
        try {
            QueryBuilder<Deck, Integer> qb = queryBuilder();
            PreparedQuery<Deck> pq = qb.where().eq("name", deckName).prepare();
            Deck deck = queryForFirst(pq);
            // Do not create a new one if exists
            if (deck != null) {
                return deck;
            }
            Deck newDeck = new Deck();
            newDeck.setName(deckName);
            newDeck.setRating(0.0);
            create(newDeck);
            // Create new one and it should exist
            deck = queryForFirst(pq);
            assert deck != null : "Deck creation failed. The query is still null!";
            return deck;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

