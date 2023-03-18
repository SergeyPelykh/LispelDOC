package com.lispel.lispeldoc.model.lispel;

import com.lispel.lispeldoc.model.abstracts.OrderItem;
import com.lispel.lispeldoc.model.abstracts.Vendor;
import com.lispel.lispeldoc.model.lispel.StickerNumber;

import java.util.ArrayList;
import java.util.List;

public abstract class PrintUnit{
    protected String name;
    protected Vendor vendor;
    protected List<StickerNumber> stickerNumbers;

    public PrintUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<StickerNumber> getStickerNumbers() {
        return stickerNumbers;
    }
    public void addStickerNumber(StickerNumber stickerNumber){
        if (stickerNumbers == null){
            stickerNumbers = new ArrayList<>();
        }
        stickerNumbers.add(stickerNumber);
    }
}
