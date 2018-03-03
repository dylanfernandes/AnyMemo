package org.liberty.android.fantastischmemo.entity;

import java.util.Date;
import com.j256.ormlite.field.DataType;
import java.lang.Math;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * Created by dylanfernandes on 2018-03-02.
 */

public class AchievementPoint {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(version = true, format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType= DataType.DATE_STRING)
    private Date time;
    @DatabaseField(defaultValue = "1")
    private Integer value;
    //@DatabaseField(foreign=true)
    //private UserStatistics stats;
    @DatabaseField
    private Tag tag;
    @DatabaseField
    private Deck deck;

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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }


}
