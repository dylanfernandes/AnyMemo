package org.liberty.android.fantastischmemo.entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.AchievementTagPointsJoinDaoImpl;


/**
 * Created by Paul on 2018-04-16.
 */

@DatabaseTable(tableName = "achievementtagpointsjoin", daoClass = AchievementTagPointsJoinDaoImpl.class)
public class AchievementTagPointsJoin {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(foreign = true)
    private TagPoints tagPoints;

    @DatabaseField(foreign = true)
    private AchievementPoint achievementPoint;

    public AchievementTagPointsJoin() {}

    public AchievementTagPointsJoin(TagPoints tagPoints, AchievementPoint achievementPoint) {
        this.tagPoints = tagPoints;
        this.achievementPoint = achievementPoint;
        tagPoints.increment(achievementPoint.getValue());
    }


    public TagPoints getTagPoints() {
        return tagPoints;
    }

    public void setJoin(AchievementPoint achievementPoint, TagPoints tagPoints) {
        this.tagPoints = tagPoints;
        this.achievementPoint = achievementPoint;
        tagPoints.increment(achievementPoint.getValue());
    }

    public AchievementPoint getAchievementPoint() {
        return achievementPoint;
    }


}
