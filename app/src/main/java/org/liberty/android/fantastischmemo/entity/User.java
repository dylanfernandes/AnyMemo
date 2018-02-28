package org.liberty.android.fantastischmemo.entity;




import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.UserDaoImpl;

import java.util.Date;

/**
 * Created by Paul on 2018-02-25.
 */

@DatabaseTable(tableName = "user", daoClass = UserDaoImpl.class)
public class User implements VersionableDomainObject{

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(defaultValue = "")
    private String surname;

    @DatabaseField(defaultValue = "")
    private String name;

    @DatabaseField(defaultValue = "")
    private String username;

    @DatabaseField(foreign = true)
    private UserStatistics userStats;



    public Date getCreationDate() {
        return null;
    }


    public void setCreationDate(Date creationDate) {

    }


    public Date getUpdateDate() {
        return null;
    }


    public void setUpdateDate(Date updateDate) {

    }
}
