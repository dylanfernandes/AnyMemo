package org.liberty.android.fantastischmemo.entity;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;

import java.lang.Math;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.UserStatisticsDaoImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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

    @ForeignCollectionField
    private ForeignCollection<AchievementPoint> points;

    public final static long MILLIS_PER_DAY = 24*60*60*1000L;


    public UserStatistics() {
    }

    //fake UserStatistics for AccountPage
    public UserStatistics(Integer longest, Integer current){
        this.longestStreak = longest;
        this.streak = current;
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

    public boolean hasPoints() {
        Boolean hasP = false;
        if(points == null)
            return hasP;

        try {
             hasP = points.size() > 0;
        }
    catch(Exception e){
        String message = e.getMessage();
    }
        return hasP;
    }

    public AchievementPoint getLatestPoint() {
        if(hasPoints()) {
            return getLastElement(points);
        }
        return null;
    }

    private static <T> T getLastElement(final ForeignCollection<T> elements) {
        final Iterator<T> itr = elements.iterator();
        T lastElement = itr.next();

        while(itr.hasNext()) {
            lastElement=itr.next();
        }

        return lastElement;
    }

    public List<AchievementPoint> getPoints() {
        Iterator<AchievementPoint> pointIterator = this.points.iterator();
        List<AchievementPoint> pointList = new ArrayList<>();
        while(pointIterator.hasNext()) {
            pointList.add(pointIterator.next());
        }
        return pointList;
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
        if(!checkStreak(today)) {
            streak = 0;
            longestStreak = 0;
            weeks = 0;
            months = 0;
        } else {
            streak ++;
        }
    }

    //returns true if streak is still active
    //An active streak is defined as:
    //  1)Less than 24 hours has passed between the last login
    //  2)The most recent login is one a different day than the previous login
    public boolean checkStreak(Date date) {
        Calendar recent = Calendar.getInstance();
        Calendar last = Calendar.getInstance();
        boolean differentDay;
        boolean lessThanDay;

        recent.setTime(date);
        last.setTime(lastLogin);

        differentDay = recent.get(Calendar.DAY_OF_YEAR) != last.get(Calendar.DAY_OF_YEAR);
        lessThanDay = Math.abs(date.getTime() - lastLogin.getTime()) < MILLIS_PER_DAY;

        return differentDay && lessThanDay;
    }

}
