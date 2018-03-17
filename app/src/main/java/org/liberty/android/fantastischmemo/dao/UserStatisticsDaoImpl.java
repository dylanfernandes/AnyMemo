package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import org.liberty.android.fantastischmemo.entity.UserStatistics;

import java.sql.SQLException;

/**
 * Created by Paul on 2018-02-27.
 */

public class UserStatisticsDaoImpl extends AbstractHelperDaoImpl<UserStatistics, Integer> implements UserStatisticsDao {
    public UserStatisticsDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<UserStatistics> tableConfig)
            throws SQLException {
        super(connectionSource, UserStatistics.class);
    }

    public UserStatisticsDaoImpl(ConnectionSource connectionSource, Class<UserStatistics> clazz)
            throws SQLException {
        super(connectionSource, clazz);
    }

}
