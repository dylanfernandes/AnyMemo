package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import org.liberty.android.fantastischmemo.entity.TagPoints;

import java.sql.SQLException;

/**
 * Created by dylanfernandes on 2018-04-15.
 */

public class TagPointsDaoImpl extends AbstractHelperDaoImpl<TagPoints, Integer> implements TagPointsDao {

    public TagPointsDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<TagPoints> config) throws SQLException {
        super(connectionSource, TagPoints.class);
    }

    public TagPointsDaoImpl(ConnectionSource connectionSource, Class<TagPoints> tPoint) throws SQLException {
        super(connectionSource, tPoint);
    }
}
