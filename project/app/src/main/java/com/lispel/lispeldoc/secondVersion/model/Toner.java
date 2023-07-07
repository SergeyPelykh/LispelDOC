package com.lispel.lispeldoc.secondVersion.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "toner_table_second")
public class Toner {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String fullName;
    private int weight;

    public Toner() {
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
