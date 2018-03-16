package org.liberty.android.fantastischmemo.dao;

import org.liberty.android.fantastischmemo.entity.User;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import java.sql.SQLException;

/**
 * Created by Paul on 2018-02-25.
 */

public class UserDaoImpl extends AbstractHelperDaoImpl<User, Integer> implements UserDao {

    public UserDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<User> tableConfig)
            throws SQLException {
        super(connectionSource, User.class);
    }

    public UserDaoImpl(ConnectionSource connectionSource, Class<User> clazz)
            throws SQLException {
        super(connectionSource, clazz);
    }
}
