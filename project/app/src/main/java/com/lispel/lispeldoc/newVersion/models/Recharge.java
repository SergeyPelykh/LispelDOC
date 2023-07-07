package com.lispel.lispeldoc.newVersion.models;


import com.lispel.lispeldoc.newVersion.interfaces.Component;
import com.lispel.lispeldoc.newVersion.interfaces.TypeService;

import java.util.ArrayList;
import java.util.List;

public class Recharge implements TypeService {
    private String name;
    private Toner toner;
    private int weight;

    public Recharge() {
        name = "recharge";
    }

    public void setToner(Toner toner) {
        this.toner = toner;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Component> getComponents() {
        ArrayList<Component> result = new ArrayList<>();
        result.add(toner);
        return result;
    }
}
