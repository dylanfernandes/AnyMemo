package org.liberty.android.fantastischmemo.entity;

import java.io.Serializable;
import java.util.Date;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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

    @DatabaseField(foreign=true)
    private UserStatistics stats;

    @DatabaseField(foreign = true)
    private DailyPoints dailyPoints;

    @DatabaseField(foreign=true)
    private DeckPoints deckPoints;

    @DatabaseField(foreign=true)
    private TagPoints tagPoints;

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

    public UserStatistics getStats() {
        return stats;
    }

    public void setStats(UserStatistics stats) {
        this.stats = stats;
    }

    public DeckPoints getDeckPoints() {
        return deckPoints;
    }

    public void setDeckPoints(DeckPoints deckP) {
        this.deckPoints = deckP;
    }

    public DailyPoints getDailyPoints() { return dailyPoints; }

    public void setDailyPoints(DailyPoints dailyPoints) {this.dailyPoints = dailyPoints; }

    public TagPoints getTagPoints() {
        return tagPoints;
    }

    public void setTagPoints(TagPoints tagP) {
        this.tagPoints = tagP;
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
