package com.lispel.lispeldoc.newVersion.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.lispel.lispeldoc.newVersion.interfaces.Component;

import java.util.Date;

@Entity(tableName = "toner_table")
public class Toner implements Component {
    @PrimaryKey
    private long id;
    private String name;
    private String detailName;
    private int weight;

    public Toner() {
        id = new Date().getTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDetailName() {
        return detailName;
    }
}
