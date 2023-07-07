package com.lispel.lispeldoc.secondVersion.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "component_table")
public class Component {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String fullName;

    public Component() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
