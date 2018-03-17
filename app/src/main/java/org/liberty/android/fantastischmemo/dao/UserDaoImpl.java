package org.liberty.android.fantastischmemo.dao;

import org.liberty.android.fantastischmemo.entity.Category;
import org.liberty.android.fantastischmemo.entity.User;
import org.liberty.android.fantastischmemo.entity.UserStatistics;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
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

    public User createOrReturn(String username) {
        try {
            QueryBuilder<User, Integer> qb = queryBuilder();
            PreparedQuery<User> pq = qb.where().eq("username", username).prepare();
            User user = queryForFirst(pq);
            // Do not create a new one if exists
            if (user != null) {
                return user;
            }
            User newUser = new User();
            newUser.setUsername(username);
            create(newUser);
            // Create new one and it should exist
            user = queryForFirst(pq);
            assert user != null : "User creation failed. The query is still null!";
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editName(String username, String name){
        try {
            UpdateBuilder<User, Integer> ub = updateBuilder();
            ub.where().eq("username", username);
            ub.updateColumnValue("name" , name);
            ub.update();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(User user) {
        UserStatistics userStat = user.getUserStatistics();
        getCentralHelper().getUserStatisticDao().delete(userStat);
        return super.delete(user);
    }
}
