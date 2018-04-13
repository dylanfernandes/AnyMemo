package org.liberty.android.fantastischmemo.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.DeckPointsDaoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dylanfernandes on 2018-04-12.
 */

@DatabaseTable(tableName = "deckpoints", daoClass = DeckPointsDaoImpl.class)
public class DeckPoints implements Serializable {
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(defaultValue = "", width = 8192)
    private String deckName;

    @ForeignCollectionField
    private ForeignCollection<AchievementPoint> points;

    @DatabaseField(defaultValue = "0")
    private Integer sum;

    public DeckPoints () {
        sum = 0;
    }

    public Integer getId() {
        return id;
    }

    public String getDeckName() { return deckName; }

    public Integer getSum() { return sum;}

    private void addSum(AchievementPoint p) {
        sum += p.getValue();
    }

    public List<AchievementPoint> getPoints() {
        Iterator<AchievementPoint> pointIterator = this.points.iterator();
        List<AchievementPoint> pointList = new ArrayList<>();
        while(pointIterator.hasNext()) {
            pointList.add(pointIterator.next());
        }
        return pointList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void addPoint(AchievementPoint point) {
        this.points.add(point);
        addSum(point);
    }
}
