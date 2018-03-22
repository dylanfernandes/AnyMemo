package org.liberty.android.fantastischmemo.dao;

import org.liberty.android.fantastischmemo.entity.User;

import com.j256.ormlite.dao.Dao;


/**
 * Created by Paul on 2018-02-25.
 */


public interface UserDao extends HelperDao<User, Integer> {


    User createOrReturn(String username);
    void editName(String username, String name);

}
