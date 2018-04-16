package org.liberty.android.fantastischmemo.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dylanfernandes on 2018-04-14.
 */

public abstract class PointGrouping {
    @DatabaseField(generatedId = true)
    private Integer id;

    @ForeignCollectionField
    private ForeignCollection<AchievementPoint> points;

    @DatabaseField(defaultValue = "0")
    private Integer sum;

    public Integer getId() {
        return id;
    }


    public PointGrouping() {
        sum = 0;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AchievementPoint> getPoints() {
        Iterator<AchievementPoint> pointIterator = this.points.iterator();
        List<AchievementPoint> pointList = new ArrayList<>();
        while(pointIterator.hasNext()) {
            pointList.add(pointIterator.next());
        }
        return pointList;
    }

    public Integer getSum() {
        sum = 0;
        Iterator<AchievementPoint> pointIterator = this.points.iterator();
        while(pointIterator.hasNext()) {
            sum += pointIterator.next().getValue();
        }
        return sum;
    }
}
