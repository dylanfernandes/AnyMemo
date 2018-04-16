package org.liberty.android.fantastischmemo.dao;


import com.j256.ormlite.dao.Dao;
import org.liberty.android.fantastischmemo.entity.DailyPoints;

/**
 * Created by Paul on 2018-04-13.
 */

public interface DailyPointsDao extends HelperDao<DailyPoints, Integer> {
    DailyPoints createOrReturn();
}
