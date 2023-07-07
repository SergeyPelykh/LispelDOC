package com.lispel.lispeldoc.newVersion.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.lispel.lispeldoc.newVersion.interfaces.Client;

import java.util.Date;

@Entity(tableName = "clientPerson_table")
public class ClientLispelPerson extends ClientLispel implements Client {
    @PrimaryKey(autoGenerate = true)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ClientLispelPerson() {
        super.setType("Person");
        id = new Date().getTime();
    }



    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getFullName() {
        return super.getFullName();
    }

    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public String getType() {
        return super.getType();
    }
}
