package com.lispel.lispeldoc.model.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public abstract class PartOfCartridge {
    private String name;
    private String volume;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
