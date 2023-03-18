package com.lispel.lispeldoc.model.lispel;

import com.lispel.lispeldoc.model.abstracts.OrderItem;

public class OrderItemLispel extends OrderItem {
    PrintUnit printUnit;
    ServiceOnPrintUnit service;

    public ServiceOnPrintUnit getService() {
        return service;
    }

    public void setService(ServiceOnPrintUnit service) {
        this.service = service;
    }

    public PrintUnit getPrintUnit() {
        return printUnit;
    }

    public void setPrintUnit(PrintUnit printUnit) {
        this.printUnit = printUnit;
    }
}
