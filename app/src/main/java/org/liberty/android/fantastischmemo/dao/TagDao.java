package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.dao.Dao;
import org.liberty.android.fantastischmemo.entity.Tag;

/**
 * Created by Adam on 2018-02-04.
 */

public interface TagDao extends Dao<Tag, Integer> {
    Tag createOrReturn(String name);
}
