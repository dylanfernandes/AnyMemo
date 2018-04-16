package org.liberty.android.fantastischmemo.dao;

import org.liberty.android.fantastischmemo.entity.DailyPoints;
import org.liberty.android.fantastischmemo.utils.DayDateUtil;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;



/**
 * Created by Paul on 2018-04-13.
 */

public class DailyPointsDaoImpl extends AbstractHelperDaoImpl<DailyPoints, Integer> implements DailyPointsDao {

    public DailyPointsDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<DailyPoints> tableConfig)
            throws SQLException {
        super(connectionSource, DailyPoints.class);
    }
    public DailyPointsDaoImpl(ConnectionSource connectionSource, Class<DailyPoints> clazz)
            throws SQLException {
        super(connectionSource, clazz);
    }

    public DailyPoints createOrReturn() {

        try {
            QueryBuilder<DailyPoints, Integer> qb = queryBuilder();
            PreparedQuery<DailyPoints> pq = qb.where().eq("time", DayDateUtil.getDayDate()).prepare();
            DailyPoints dailyPoints = queryForFirst(pq);
            if(dailyPoints != null) {
                return dailyPoints;
            }
            DailyPoints newDailyPoints = new DailyPoints();
            create(newDailyPoints);
            // Create new one and it should exist
            dailyPoints = queryForFirst(pq);
            assert dailyPoints != null : "Statistics creation failed. The query is still null!";
            return dailyPoints;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public DailyPoints createOrReturn(DailyPoints dpObject) {

        try {
            QueryBuilder<DailyPoints, Integer> qb = queryBuilder();
            PreparedQuery<DailyPoints> pq = qb.where().eq("time", dpObject.getTime()).prepare();
            DailyPoints dailyPoints = queryForFirst(pq);
            if(dailyPoints != null) {
                return dailyPoints;
            }
            DailyPoints newDailyPoints = new DailyPoints();
            create(newDailyPoints);
            // Create new one and it should exist
            dailyPoints = queryForFirst(pq);
            assert dailyPoints != null : "Statistics creation failed. The query is still null!";
            return dailyPoints;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
