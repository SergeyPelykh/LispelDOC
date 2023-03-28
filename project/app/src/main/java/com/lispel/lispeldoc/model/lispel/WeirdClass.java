package com.lispel.lispeldoc.model.lispel;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.Date;

import com.lispel.lispeldoc.model.utility.Convert;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "weird_table")
@TypeConverters({Convert.class})
public class WeirdClass {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "number")
    private String number;
    @ColumnInfo(name = "client")
    private String client;
    @ColumnInfo(name = "cartridge")
    private String cartridge;
    @ColumnInfo(name = "service")
    private String service;
    @ColumnInfo(name = "comment")
    private String comment;
    @ColumnInfo(name = "date_of_create")
    @TypeConverters({Convert.class})
    private Date date_of_create;


    @ColumnInfo(name = "date_of_last_edit")
    @TypeConverters({Convert.class})
    private Date date_of_last_edit;

    public WeirdClass() {
        this.date_of_create = new Date();
    }
    @Ignore
    public WeirdClass(String number) {
        this.number = number;
        this.date_of_create = new Date();
    }

    public Date getDate_of_last_edit() {
        return date_of_last_edit;
    }

    public void setDate_of_last_edit(Date date_of_last_edit) {
        this.date_of_last_edit = date_of_last_edit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCartridge() {
        return cartridge;
    }

    public void setCartridge(String cartridge) {
        this.cartridge = cartridge;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate_of_create() {
        return date_of_create;
    }

    public void setDate_of_create(Date date_of_create) {
        this.date_of_create = date_of_create;
    }
}
