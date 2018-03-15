package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import org.liberty.android.fantastischmemo.entity.Tag;

import java.sql.SQLException;

/**
 * Created by Adam on 2018-02-04.
 */

public class TagDaoImpl extends AbstractHelperDaoImpl<Tag, Integer> implements TagDao {

    public TagDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Tag> config)
        throws SQLException {
            super(connectionSource, Tag.class);
    }

    public TagDaoImpl(ConnectionSource connectionSource, Class<Tag> clazz)
        throws SQLException {
            super(connectionSource, clazz);
    }

    public Tag createOrReturn(String name) {
        try {
            QueryBuilder<Tag, Integer> qb = queryBuilder();
            PreparedQuery<Tag> pq = qb.where().eq("name", name).prepare();
            Tag c = queryForFirst(pq);
            // Do not create a new one if exists
            if (c != null) {
                return c;
            }
            Tag nt = new Tag();
            nt.setName(name);
            create(nt);
            // Create new one and it should exist
            c = queryForFirst(pq);
            assert c != null : "Tag creation failed. The query is still null!";
            return c;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
