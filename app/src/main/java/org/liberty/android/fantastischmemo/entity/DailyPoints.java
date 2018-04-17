package org.liberty.android.fantastischmemo.entity;

import org.liberty.android.fantastischmemo.dao.DailyPointsDaoImpl;
import org.liberty.android.fantastischmemo.utils.DayDateUtil;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;


/**
 * Created by Paul on 2018-04-13.
 */
@DatabaseTable(tableName = "dailypoints", daoClass = DailyPointsDaoImpl.class)
public class DailyPoints extends PointGrouping {

    @DatabaseField(defaultValue = "")
    private String time;

    public DailyPoints() {
        setTime();
    }

    public String getTime() {
        return time;
    }

    private void setTime() {
        this.time = DayDateUtil.getDayDateString();
    }



}
