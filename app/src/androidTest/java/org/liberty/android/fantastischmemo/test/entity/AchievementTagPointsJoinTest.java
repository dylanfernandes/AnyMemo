package org.liberty.android.fantastischmemo.test.entity;

import org.junit.Test;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.entity.AchievementTagPointsJoin;
import org.liberty.android.fantastischmemo.entity.TagPoints;

import static org.junit.Assert.assertEquals;


/**
 * Created by Paul on 2018-04-16.
 */

public class AchievementTagPointsJoinTest {

    @Test
    public void testJoin() {
        TagPoints tagPoints = new TagPoints();
        tagPoints.setId(1);
        tagPoints.setTagName("pie");

        AchievementPoint achievementPoint = new AchievementPoint();
        achievementPoint.setValue(5);

        AchievementTagPointsJoin join = new AchievementTagPointsJoin(tagPoints, achievementPoint);

        assertEquals(5, (int)join.getAchievementPoint().getValue());
        assertEquals("pie", join.getTagPoints().getTagName());


    }
}
