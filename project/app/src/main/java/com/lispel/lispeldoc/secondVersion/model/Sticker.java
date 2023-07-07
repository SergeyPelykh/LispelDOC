package com.lispel.lispeldoc.secondVersion.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.lispel.lispeldoc.model.utility.Convert;

import java.util.Date;

@Entity(tableName = "sticker_table")
@TypeConverters({Convert.class})
public class Sticker {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String number;

    @TypeConverters({Convert.class})
    private Date dateOfCreate;



    public Sticker() {
        this.dateOfCreate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }
}
