package com.lispel.lispeldoc.secondVersion.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.lispel.lispeldoc.model.utility.Convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Entity(tableName = "cartridge_table_second")
@TypeConverters({Convert.class})
public class Cartridge {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String model;
    private String vendor;
    private boolean originality;
    private long owner;
    @TypeConverters({Convert.class})
    private ArrayList<Long> stickers;


    public Cartridge() {
        this.stickers = new ArrayList<>();
    }

    public ArrayList<Long> getStickers() {
        return stickers;
    }

    public void setStickers(ArrayList<Long> stickers) {
        this.stickers = stickers;
    }

    public void addSticker(Long id){
        System.out.println("from addSticker()");
        this.stickers.add(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public boolean isOriginality() {
        return originality;
    }

    public void setOriginality(boolean originality) {
        this.originality = originality;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public void addOwner(long owner) {
        this.owner = owner;
    }
}
