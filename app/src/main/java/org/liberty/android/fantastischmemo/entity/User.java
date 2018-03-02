package org.liberty.android.fantastischmemo.entity;




import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.UserDaoImpl;

import java.util.Date;

/**
 * Created by Paul on 2018-02-25.
 */

@DatabaseTable(tableName = "user", daoClass = UserDaoImpl.class)
public class User implements VersionableDomainObject{

    //Attributes
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(defaultValue = "")
    private String surname;

    @DatabaseField(defaultValue = "")
    private String name;

    @DatabaseField(defaultValue = "")
    private String username;

    @DatabaseField(foreign = true)
    private UserStatistics userStatistics;

    @DatabaseField(format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType= DataType.DATE_STRING)
    private Date creationDate;

    @DatabaseField(format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType=DataType.DATE_STRING)
    private Date updateDate;

    public User() {}

    //Setters and Getters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserStatistics getUserStatistics() {
        return this.userStatistics;
    }

    public void setUserStatistics(UserStatistics userStatistics) {
        this.userStatistics = userStatistics;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString(){
        return "User [id=" + id + ", surname=" + surname
                + ", name=" + name + ", username=" + username +  "]";
    }
}
