package org.liberty.android.fantastischmemo.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.liberty.android.fantastischmemo.dao.TagDaoImpl;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Created by Adam on 2018-02-04.
 */

@DatabaseTable(tableName = "tags", daoClass = TagDaoImpl.class)
public class Tag implements Serializable {
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(name)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag))
            return false;
        if (obj == this)
            return true;
        if (this.getName().equals(((Tag) obj).getName()))
            return true;
        else
            return false;
    }
}
