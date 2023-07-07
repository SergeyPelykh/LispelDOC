package com.lispel.lispeldoc.newVersion.models;


import com.lispel.lispeldoc.newVersion.interfaces.Client;
import com.lispel.lispeldoc.newVersion.interfaces.Order;
import com.lispel.lispeldoc.newVersion.interfaces.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderLispel implements Order {
    private Client client;
    private List<Service> services;
    private int price;
    private Date dateOfCreate;

    public OrderLispel() {
        dateOfCreate = new Date();
        services = new ArrayList<>();
    }
    public void addService(Service service){
        services.add(service);
    }
    @Override
    public void setClient(Client client) {
        this.client = client;
    }
    @Override
    public void setServices(List<Service> services) {
        this.services= services;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public List<Service> getServices() {
        return services;
    }

    @Override
    public int getPrice() {
        if (services.isEmpty()){
            return 0;
        } else {
            return services.stream().map(Service::getPrice).reduce(0, Integer::sum);
        }
    }

    @Override
    public Date getDateOfCreate() {
        return dateOfCreate;
    }
}
