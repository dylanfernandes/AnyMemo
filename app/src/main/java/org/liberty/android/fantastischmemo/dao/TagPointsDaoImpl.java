package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
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

    public TagPoints createOrReturn(String tagName) {
        try {
            QueryBuilder<TagPoints, Integer> qb = queryBuilder();
            PreparedQuery<TagPoints> pq = qb.where().eq("tagName", tagName).prepare();
            TagPoints tagPoints = queryForFirst(pq);
            if(tagPoints != null) {
                return tagPoints;
            }
            TagPoints newTagPoints = new TagPoints();
            newTagPoints.setTagName(tagName);
            create(newTagPoints);
            // Create new one and it should exist
            tagPoints = queryForFirst(pq);
            assert tagPoints != null : "Statistics creation failed. The query is still null!";
            return tagPoints;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
