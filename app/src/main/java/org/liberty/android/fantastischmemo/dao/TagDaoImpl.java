package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import org.liberty.android.fantastischmemo.entity.Tag;

import java.sql.SQLException;

/**
 * Created by Adam on 2018-02-04.
 */

public class TagDaoImpl extends BaseDaoImpl<Tag, Integer> implements TagDao {

    public TagDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Tag> config)
        throws SQLException {
            super(connectionSource, Tag.class);
    }

    public TagDaoImpl(ConnectionSource connectionSource, Class<Tag> clazz)
        throws SQLException {
            super(connectionSource, clazz);
    }

}
