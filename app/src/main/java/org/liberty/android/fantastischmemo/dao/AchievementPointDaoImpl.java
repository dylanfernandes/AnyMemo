package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import org.liberty.android.fantastischmemo.entity.AchievementPoint;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dylanfernandes on 2018-03-15.
 */

public class AchievementPointDaoImpl extends AbstractHelperDaoImpl<AchievementPoint, Integer> implements AchievementPointDao {

    public AchievementPointDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<AchievementPoint> config) throws SQLException {
        super(connectionSource, AchievementPoint.class);
    }

    public AchievementPointDaoImpl(ConnectionSource connectionSource, Class<AchievementPoint> aPoint) throws SQLException {
        super(connectionSource, aPoint);
    }
}
