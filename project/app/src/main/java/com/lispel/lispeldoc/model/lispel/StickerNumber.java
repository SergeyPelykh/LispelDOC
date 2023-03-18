package com.lispel.lispeldoc.model.lispel;

import java.util.Calendar;
import java.util.Date;

public class StickerNumber {
    private String number;
    private Date dateOfCreate;

    public StickerNumber(String number) {
        this.number = number;
        this.dateOfCreate = new Date();
    }

    public String getNumber() {
        return number;
    }


    public Date getDateOfCreate() {
        return dateOfCreate;
    }

}
