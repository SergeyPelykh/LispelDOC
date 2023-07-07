package com.lispel.lispeldoc.newVersion.interfaces;

import java.util.Date;
import java.util.List;

public interface Order {
    Client getClient();
    void setClient(Client client);
    List<Service> getServices();
    void setServices(List<Service> services);
    int getPrice();
    Date getDateOfCreate();
}
