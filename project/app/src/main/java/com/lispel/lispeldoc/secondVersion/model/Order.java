package com.lispel.lispeldoc.secondVersion.model;

import java.util.Date;
import java.util.List;

public class Order {
    private long id;
    private Client client;
    private Date date;
    private List<Service> services;
    private int price;
}
