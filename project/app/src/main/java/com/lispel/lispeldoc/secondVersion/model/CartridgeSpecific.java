package com.lispel.lispeldoc.secondVersion.model;


import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.lispel.lispeldoc.secondVersion.inteface.GetListOfFields;
import com.lispel.lispeldoc.secondVersion.inteface.ListedFields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(tableName = "cartridge_specific_table")
public class CartridgeSpecific implements GetListOfFields, ListedFields {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String model;
    private String vendor;
    private boolean originality;

    public CartridgeSpecific() {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public ArrayList<String> getNameAllFields() {
        return Stream.of("модель", "1", "вендор", "1", "оригинальность", "1")
                .collect(Collectors.toCollection(ArrayList::new));
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

    @Override
    public ArrayList<String> getListOfField() {
        return null;
    }
    private static ArrayList<String> fields = new ArrayList<>(Arrays.asList(
            "vendor", "cartridgeModel", "originality"));
}
