package org.liberty.android.fantastischmemo.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.TagPointsDaoImpl;

/**
 * Created by dylanfernandes on 2018-04-15.
 */

@DatabaseTable(tableName = "tagpoints", daoClass = TagPointsDaoImpl.class)
public class TagPoints {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(defaultValue = "", width = 8192)
    private String tagName;

    @DatabaseField(defaultValue = "0")
    private Integer sum;

    public TagPoints() {sum=0;}

    public TagPoints(String name){
        tagName = name;
        sum=0;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void increment(Integer points) {
        sum += points;
    }

    public Integer getSum(){
        return sum;
    }


}
