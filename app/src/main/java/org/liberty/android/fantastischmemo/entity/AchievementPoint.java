package org.liberty.android.fantastischmemo.entity;

import java.io.Serializable;
import java.util.Date;
import com.j256.ormlite.field.DataType;
import java.lang.Math;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.AchievementPointDao;
import org.liberty.android.fantastischmemo.dao.AchievementPointDaoImpl;

/**
 * Created by dylanfernandes on 2018-03-02.
 */

@DatabaseTable(tableName = "achievementpoints", daoClass = AchievementPointDaoImpl.class)
public class AchievementPoint implements Serializable, VersionableDomainObject{
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(version = true, format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType= DataType.DATE_STRING)
    private Date time;
    @DatabaseField(defaultValue = "1")
    private Integer value;
    //@DatabaseField(foreign=true)
    //private UserStatistics stats;
    @DatabaseField(foreign = true)
    private Tag tag;

    @DatabaseField(foreign = true)
    private Deck deck;

    @DatabaseField(format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType= DataType.DATE_STRING)
    private Date creationDate;

    @DatabaseField(format="yyyy-MM-dd HH:mm:ss.SSSSSS", dataType=DataType.DATE_STRING)
    private Date updateDate;

    public AchievementPoint () {}

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


    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date cDate) {
        creationDate = cDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date uDate) {
        updateDate = uDate;
    }
}
