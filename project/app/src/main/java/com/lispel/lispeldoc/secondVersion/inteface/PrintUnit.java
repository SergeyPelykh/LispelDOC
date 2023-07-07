package com.lispel.lispeldoc.secondVersion.inteface;

import com.lispel.lispeldoc.newVersion.interfaces.Client;
import com.lispel.lispeldoc.newVersion.interfaces.Sticker;

import java.util.List;

public interface PrintUnit {
    String getName();
    List<Sticker> getStickers();
    Sticker getCurrentSticker();
    Client getOwner();
}
