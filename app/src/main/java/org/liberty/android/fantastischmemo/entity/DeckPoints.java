package org.liberty.android.fantastischmemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dylanfernandes on 2018-04-12.
 */

public class DeckPoints implements Serializable {

    private Integer id;

    private String deckName;

    private List<AchievementPoint> points;

    public DeckPoints () { points = new ArrayList<AchievementPoint>();}

    public Integer getId() {
        return id;
    }

    public String getDeckName() { return deckName; }

    public List<AchievementPoint> getPoints() {
        return points;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void setPoint(AchievementPoint point) {
        this.points.add(point);
    }
}
