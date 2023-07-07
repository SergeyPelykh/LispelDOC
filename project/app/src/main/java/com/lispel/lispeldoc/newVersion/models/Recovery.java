package com.lispel.lispeldoc.newVersion.models;


import com.lispel.lispeldoc.newVersion.interfaces.Component;
import com.lispel.lispeldoc.newVersion.interfaces.TypeService;

import java.util.ArrayList;
import java.util.List;

public class Recovery implements TypeService {
    private String name;
    private Component component;

    public Recovery() {
        this.name = "recovery ";
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public String getName() {
        return name + component.getName();
    }

    @Override
    public List<Component> getComponents() {
        ArrayList<Component> result = new ArrayList<>();
        result.add(component);
        return result;
    }
}
