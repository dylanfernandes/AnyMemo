package org.liberty.android.fantastischmemo.dao;

import org.liberty.android.fantastischmemo.entity.DailyPoints;
import com.j256.ormlite.dao.BaseDaoImpl;
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

}
