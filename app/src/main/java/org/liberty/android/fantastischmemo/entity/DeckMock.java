package org.liberty.android.fantastischmemo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DeckMock implements Parcelable {

    private String name;
    private String dbPath;
    private List<Tag> tags;

    public DeckMock(String name, String dbPath) {
        this(name, dbPath, new ArrayList<Tag>());
    }

    public DeckMock(String name, String dbPath, List<Tag> tags) {
        this.name = name;
        this.dbPath = dbPath;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void deleteTag(Tag tag) {
        tags.remove(tag);
    }

    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(dbPath);
        dest.writeList(tags);
    }

    private DeckMock(Parcel data) {
        name = data.readString();
        dbPath = data.readString();
        tags = new ArrayList<>();
        data.readList(tags, Tag.class.getClassLoader());
    }

    public static final Parcelable.Creator<DeckMock> CREATOR = new Parcelable.Creator<DeckMock>() {
        @Override
        public DeckMock createFromParcel(Parcel source) {
            return new DeckMock(source);
        }

        @Override
        public DeckMock[] newArray(int size) {
            return new DeckMock[size];
        }
    };
}
