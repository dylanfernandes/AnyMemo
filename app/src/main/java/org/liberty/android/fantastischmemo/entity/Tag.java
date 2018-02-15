package org.liberty.android.fantastischmemo.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.TagDaoImpl;

/**
 * Created by Adam on 2018-02-04.
 */

@DatabaseTable(tableName = "tags", daoClass = TagDaoImpl.class)
public class Tag {
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String name;

    public Tag(){
        //empty constructor for ormlite
    }

    public Tag(String name){
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
