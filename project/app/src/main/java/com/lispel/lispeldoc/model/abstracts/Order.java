package com.lispel.lispeldoc.model.abstracts;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Order {
    protected long id;
    protected Date date;
    protected Client client;
    protected List<OrderItem> items;
    protected int price;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public int getPrice() {
        return price;
    }

    public void addItem(OrderItem orderItem){
        if (items == null){
            items = new ArrayList<>();
        }
        items.add(orderItem);
    }
}
