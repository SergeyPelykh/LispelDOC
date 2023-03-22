package com.lispel.lispeldoc.model.lispel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.lispel.lispeldoc.model.abstracts.Client;
import com.lispel.lispeldoc.model.abstracts.ClientType;
import com.lispel.lispeldoc.model.utility.Convert;

@Entity(tableName = "client_lispel")
@TypeConverters({Convert.class})
public class ClientLispel {
    @PrimaryKey(autoGenerate = true)
    protected int id;
    @ColumnInfo(name = "name")
    protected String name;
    @ColumnInfo(name = "fullName")
    protected String fullName;
    @ColumnInfo(name = "address")
    protected String address;
    @ColumnInfo(name = "client_type")
    @TypeConverters({Convert.class})
    protected ClientType clientType;

    public ClientLispel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}
