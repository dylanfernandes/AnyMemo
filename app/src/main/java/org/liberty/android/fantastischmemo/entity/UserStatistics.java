package org.liberty.android.fantastischmemo.entity;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.UserStatisticsDaoImpl;

import java.util.Date;

/**
 * Created by Paul on 2018-02-26.
 */

@DatabaseTable(tableName = "userstatistics", daoClass = UserStatisticsDaoImpl.class)
public class UserStatistics implements VersionableDomainObject{

    @DatabaseField(generatedId = true)
    private Integer statisticsId;

    @DatabaseField(version = true, format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType= DataType.DATE_STRING)
    private Date lastLogin;

    @DatabaseField(foreign = true)
    private Integer pointsId;

    @DatabaseField(generatedId = true)
    private Integer multiplier;

    @DatabaseField(generatedId = true)
    private Integer streak;

    @DatabaseField(generatedId = true)
    private Integer longestStreak;

    @DatabaseField(generatedId = true)
    private Integer weeks;

    @DatabaseField(generatedId = true)
    private Integer months;

    @DatabaseField(foreign = true)
    private int userId;

    @Override
    public Date getCreationDate() {
        return null;
    }

    @Override
    public void setCreationDate(Date creationDate) {

    }

    @Override
    public Date getUpdateDate() {
        return null;
    }

    @Override
    public void setUpdateDate(Date updateDate) {

    }
}
