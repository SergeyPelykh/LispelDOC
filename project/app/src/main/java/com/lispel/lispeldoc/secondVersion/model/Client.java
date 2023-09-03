package com.lispel.lispeldoc.secondVersion.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.lispel.lispeldoc.secondVersion.inteface.GetListOfFields;
import com.lispel.lispeldoc.secondVersion.inteface.ListedFields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(tableName = "client_table")
public class Client implements GetListOfFields, ListedFields {
    private static ArrayList<String> fields = new ArrayList<>(Arrays.asList("name", "fullName",
            "address", "phone", "clientType"));

    @Override
    public ArrayList<String> getListOfField() {
        return fields;
    }

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String fullName;
    private String address;
    private String phone;
    private String type;

    public Client(long id) {
        this.id = id;
    }

    public Client() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public ArrayList<String> getNameAllFields() {
        ArrayList<String> result = Stream.of("имя", "1", "полное имя", "1", "адрес", "1", "телефон", "3", "тип",  "1").collect(Collectors.toCollection(ArrayList::new));
        return result;
    }


}
