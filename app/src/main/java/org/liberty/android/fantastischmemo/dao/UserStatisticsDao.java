package org.liberty.android.fantastischmemo.dao;


import com.j256.ormlite.dao.Dao;
import org.liberty.android.fantastischmemo.entity.UserStatistics;
import org.liberty.android.fantastischmemo.entity.User;


/**
 * Created by Paul on 2018-02-26.
 */



public interface UserStatisticsDao extends HelperDao<UserStatistics, Integer> {

    UserStatistics createOrReturn(User user);

}
