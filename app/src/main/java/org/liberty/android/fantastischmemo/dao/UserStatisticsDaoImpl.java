package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import org.liberty.android.fantastischmemo.entity.User;
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


    public UserStatistics createOrReturn(User user) {

        try {
            int userId = user.getId();
            QueryBuilder<UserStatistics, Integer> qb = queryBuilder();
            PreparedQuery<UserStatistics> pq = qb.where().eq("userId", userId).prepare();
            UserStatistics stats = queryForFirst(pq);
            if(stats != null) {
                return stats;
            }
            UserStatistics userStats = new UserStatistics();
            userStats.setUserId(userId);
            create(userStats);
            // Create new one and it should exist
            stats = queryForFirst(pq);
            assert stats != null : "Tag creation failed. The query is still null!";
            return stats;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
