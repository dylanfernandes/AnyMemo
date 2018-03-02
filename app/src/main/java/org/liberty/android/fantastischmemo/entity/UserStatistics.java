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

    //Attributes
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(version = true, format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType= DataType.DATE_STRING)
    private Date lastLogin;

    @DatabaseField(foreign = true)
    private Points points;

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
    private User user;

    @DatabaseField(format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType= DataType.DATE_STRING)
    private Date creationDate;

    @DatabaseField(format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType=DataType.DATE_STRING)
    private Date updateDate;


    //Getters and Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }

    public Integer getStreak() {
        return streak;
    }

    public void setStreak(Integer streak) {
        this.streak = streak;
    }

    public Integer getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(Integer longestStreak) {
        this.longestStreak = longestStreak;
    }

    public Integer getWeeks() {
        return weeks;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString(){
        return "User [id=" + id + ", lastLogin=" + lastLogin
                + ", multiplier=" + multiplier + ", streak=" + streak
                + ", longestStreak=" + longestStreak + ",weeks=" + weeks
                + ", months" + months + ", user" + user + "]";
    }
}
