package org.liberty.android.fantastischmemo.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.TagPointsDaoImpl;

/**
 * Created by dylanfernandes on 2018-04-15.
 */

@DatabaseTable(tableName = "tagpoints", daoClass = TagPointsDaoImpl.class)
public class TagPoints extends PointGrouping {

    @DatabaseField(defaultValue = "", width = 8192)
    private String tagName;

    public TagPoints() {}

    public TagPoints(String name){
        tagName = name;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}
