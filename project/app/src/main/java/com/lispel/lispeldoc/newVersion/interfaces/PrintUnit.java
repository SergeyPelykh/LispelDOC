package com.lispel.lispeldoc.newVersion.interfaces;

import java.util.List;

public interface PrintUnit {
    String getName();
    List<Sticker> getStickers();
    Sticker getCurrentSticker();
    Client getOwner();
}
