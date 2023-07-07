package com.lispel.lispeldoc.newVersion.models;


import com.lispel.lispeldoc.newVersion.interfaces.Component;

public class PhotoDrum implements Component {
    private String name;
    private String detailName;

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.detailName = fullName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDetailName() {
        return detailName;
    }
}
