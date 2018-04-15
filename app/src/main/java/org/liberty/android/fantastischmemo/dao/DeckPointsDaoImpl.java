package org.liberty.android.fantastischmemo.dao;

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
}
