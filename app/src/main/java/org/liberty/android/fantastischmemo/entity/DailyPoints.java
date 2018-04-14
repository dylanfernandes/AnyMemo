package org.liberty.android.fantastischmemo.entity;

import org.liberty.android.fantastischmemo.dao.DailyPointsDaoImpl;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Paul on 2018-04-13.
 */
@DatabaseTable(tableName = "dailypoints", daoClass = DailyPointsDaoImpl.class)
public class DailyPoints {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(version = true, format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType= DataType.DATE_STRING)
    private Date time;

    @DatabaseField(defaultValue = "0")
    private Integer totalPoints;

    @ForeignCollectionField
    private ForeignCollection<AchievementPoint> points;

    public DailyPoints() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getTotalPoints() {
        return this.totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public ForeignCollection<AchievementPoint> getPoints() {
        return points;
    }

    public void addPoints(AchievementPoint newPoint) {
        points.add(newPoint);
    }

    public List<AchievementPoint> getPointList() {
        Iterator<AchievementPoint> pointIterator = this.points.iterator();
        List<AchievementPoint> pointList = new ArrayList<>();
        while(pointIterator.hasNext()) {
            pointList.add(pointIterator.next());
        }
        return pointList;
    }



}
