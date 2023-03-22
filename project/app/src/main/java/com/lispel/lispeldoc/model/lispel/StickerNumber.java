package com.lispel.lispeldoc.model.lispel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.lispel.lispeldoc.model.utility.Convert;

import java.util.Date;

@Entity(tableName = "sticker_number")
@TypeConverters({Convert.class})
public class StickerNumber {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "number")
    private String number;
    @ColumnInfo(name = "date_of_create")
    @TypeConverters({Convert.class})
    private Date dateOfCreate;

    public StickerNumber(String number) {
        this.number = number;
        this.dateOfCreate = new Date();
    }

    public String getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
