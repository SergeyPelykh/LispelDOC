package com.lispel.lispeldoc.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.model.lispel.ClientLispel;
import com.lispel.lispeldoc.model.lispel.StickerNumber;

import java.util.List;

public interface ClientLispelDAO {
    @Insert()
    void insert (ClientLispel clientLispel);

    @Query("SELECT * FROM client_lispel")
    LiveData<List<ClientLispel>> getAllClientLispel();

    @Query("DELETE FROM sticker_number")
    void deleteAll();
}
