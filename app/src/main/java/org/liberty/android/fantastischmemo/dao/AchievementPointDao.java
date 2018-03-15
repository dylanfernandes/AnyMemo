package org.liberty.android.fantastischmemo.dao;

import com.j256.ormlite.dao.Dao;

import org.liberty.android.fantastischmemo.entity.AchievementPoint;

import java.util.List;

/**
 * Created by dylanfernandes on 2018-03-15.
 */

public interface PointDao {
    List<AchievementPoint> getAllPoints();
    

}
