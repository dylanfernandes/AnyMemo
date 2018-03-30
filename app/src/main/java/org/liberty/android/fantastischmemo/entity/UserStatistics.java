package org.liberty.android.fantastischmemo.entity;


import android.graphics.Point;

import com.j256.ormlite.field.DataType;


import java.io.Serializable;
import java.lang.Math;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.UserStatisticsDaoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Paul on 2018-02-26.
 */

@DatabaseTable(tableName = "userstatistics", daoClass = UserStatisticsDaoImpl.class)
public class UserStatistics {

    //Attributes
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(foreign = true)
    private User user;

    @DatabaseField(version = true, format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType= DataType.DATE_STRING)
    private Date lastLogin;

    @DatabaseField(defaultValue = "1")
    private Integer multiplier = 1;

    @DatabaseField(defaultValue = "0")
    private Integer streak = 0;

    @DatabaseField(defaultValue = "0")
    private Integer longestStreak = 0;

    @DatabaseField(defaultValue = "0")
    private Integer weeks = 0;

    @DatabaseField(defaultValue = "0")
    private Integer months = 0;

    public List<AchievementPoint> points;

    public final static long MILLIS_PER_DAY = 24*60*60*1000L;


    public UserStatistics() { points = new ArrayList<AchievementPoint>();}

    //fake UserStatistics for AccountPage
    public UserStatistics(Integer longest, Integer current){
        this.longestStreak = longest;
        this.streak = current;
        points = new ArrayList<AchievementPoint>();
    }

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

    public boolean hasPoints() {return points.size() > 0;}

    public AchievementPoint getLatestPoint() {
        if(hasPoints()) {
            return points.get(points.size() - 1);
        }
        return null;
    }

    public void addPoint(AchievementPoint point) { points.add(point);}

    public List<AchievementPoint> getPoints() {
        return points;
    }

    public void setPoints(List<AchievementPoint> points) {
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
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;

    }


    @Override
    public String toString(){
        return "User [id=" + id + ", lastLogin=" + lastLogin
                + ", multiplier=" + multiplier + ", streak=" + streak
                + ", longestStreak=" + longestStreak + ",weeks=" + weeks
                + ", months" + months +  "]";
    }


    //Calulating Streaks
    public void streakToLongestStreak() {
        if(streak > longestStreak) {
            longestStreak = streak;
        }
    }

    public void streakToWeeks() {
        if(streak == 0) {
            weeks = 0;
        } else if(streak%7 == 0) {
            weeks = streak/7;
        }
    }

    public void streakToMonths() {
        if(streak == 0) {
            months = 0;
        } else if(streak%30 == 0) {
            months = streak/30;
        }
    }

    //Run at login
    //Sets streaks to 0 if more than a day since last login

    public void updateStreaks() {
        Date today = new Date();
        if(checkStreak(today)) {
            streak = 0;
            longestStreak = 0;
            weeks = 0;
            months = 0;
        } else {
            streak ++;
        }
    }

    public boolean checkStreak(Date date) {
        return Math.abs(date.getTime() - lastLogin.getTime()) > MILLIS_PER_DAY;
    }

}
