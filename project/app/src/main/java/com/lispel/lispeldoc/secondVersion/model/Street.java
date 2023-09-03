package com.lispel.lispeldoc.secondVersion.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "street_table")
public class Street {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;

    private String name;

    public Street() {
    }

    public Street(String title, String name) {
        this.title = title;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
