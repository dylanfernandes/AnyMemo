package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import org.liberty.android.fantastischmemo.entity.DeckPoints;

import java.sql.SQLException;

/**
 * Created by dylanfernandes on 2018-04-13.
 */

public class DeckPointsDaoImpl extends AbstractHelperDaoImpl<DeckPoints, Integer> implements DeckPointsDao {

    public DeckPointsDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<DeckPoints> config) throws SQLException {
        super(connectionSource, DeckPoints.class);
    }

    public DeckPointsDaoImpl(ConnectionSource connectionSource, Class<DeckPoints> dPoint) throws SQLException {
        super(connectionSource, dPoint);
    }

   public DeckPoints createOrReturn(String name) {
        try {
            QueryBuilder<DeckPoints, Integer> qb = queryBuilder();
            PreparedQuery<DeckPoints> pq = qb.where().eq("deckName", name).prepare();
            DeckPoints deckPoints = queryForFirst(pq);
            if(deckPoints != null) {
                return deckPoints;
            }
            DeckPoints newDeckPoints = new DeckPoints();
            newDeckPoints.setDeckName(name);
            create(newDeckPoints);
            // Create new one and it should exist
            deckPoints = queryForFirst(pq);
            assert deckPoints != null : "Statistics creation failed. The query is still null!";
            return deckPoints;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
