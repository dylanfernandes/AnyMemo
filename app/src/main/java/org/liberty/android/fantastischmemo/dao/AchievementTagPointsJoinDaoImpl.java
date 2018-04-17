package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import org.liberty.android.fantastischmemo.entity.AchievementTagPointsJoin;

import java.sql.SQLException;

/**
 * Created by Paul on 2018-04-16.
 */

public class AchievementTagPointsJoinDaoImpl extends AbstractHelperDaoImpl<AchievementTagPointsJoin, Integer> implements AchievementTagPointsJoinDao {

    public AchievementTagPointsJoinDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<AchievementTagPointsJoin> config) throws SQLException {
        super(connectionSource, AchievementTagPointsJoin.class);
    }

    public AchievementTagPointsJoinDaoImpl(ConnectionSource connectionSource, Class<AchievementTagPointsJoin> aPoint) throws SQLException {
        super(connectionSource, aPoint);
    }
}
