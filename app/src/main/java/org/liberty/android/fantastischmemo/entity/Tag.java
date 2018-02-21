package org.liberty.android.fantastischmemo.entity;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Tag {
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(name)
                .toHashCode();
    }
}
