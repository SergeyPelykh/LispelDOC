package com.lispel.lispeldoc.newVersion.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.lispel.lispeldoc.model.abstracts.ListViewDisplaible;
import com.lispel.lispeldoc.model.utility.Convert;
import com.lispel.lispeldoc.newVersion.interfaces.Sticker;

import java.util.Date;

@Entity(tableName = "sticker_table_first")
@TypeConverters({Convert.class})
public class StickerLispel implements Sticker, ListViewDisplaible {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String number;
    @TypeConverters({Convert.class})
    private Date dateOfCreate;
    @TypeConverters({Convert.class})
    private Date dateOfEdit;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public Date getDateOfEdit() {
        return dateOfEdit;
    }

    public StickerLispel() {
        dateOfCreate = new Date();
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDateOfEdit(Date dateOfEdit) {
        this.dateOfEdit = dateOfEdit;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    @Override
    public String getDescription() {
        return number;
    }
}
