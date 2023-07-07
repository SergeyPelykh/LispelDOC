package com.lispel.lispeldoc.newVersion.models;


import com.lispel.lispeldoc.newVersion.interfaces.PrintUnit;
import com.lispel.lispeldoc.newVersion.interfaces.Service;
import com.lispel.lispeldoc.newVersion.interfaces.TypeService;

public class ServiceLispel implements Service {
    private TypeService typeService;
    private PrintUnit printUnit;
    private int price;

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public void setPrintUnit(PrintUnit printUnit) {
        this.printUnit = printUnit;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        return typeService.getName() + " " + printUnit.getName();
    }

    @Override
    public TypeService getTypeService() {
        return typeService;
    }

    @Override
    public PrintUnit getPrintUnit() {
        return printUnit;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
