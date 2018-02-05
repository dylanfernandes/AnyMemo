package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import org.liberty.android.fantastischmemo.entity.DeckTag;

import java.sql.SQLException;

/**
 * Created by Adam on 2018-02-04.
 */

public class DeckTagDaoImpl extends BaseDaoImpl<DeckTag, Integer> implements DeckTagDao {

    public DeckTagDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<DeckTag> config)
        throws SQLException {
            super(connectionSource, DeckTag.class);
    }

    public DeckTagDaoImpl(ConnectionSource connectionSource, Class<DeckTag> clazz)
        throws SQLException {
            super(connectionSource, clazz);
    }

}
