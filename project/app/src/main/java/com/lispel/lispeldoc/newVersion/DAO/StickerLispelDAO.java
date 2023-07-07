package com.lispel.lispeldoc.newVersion.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.model.lispel.WeirdClass;
import com.lispel.lispeldoc.newVersion.models.StickerLispel;

import java.util.List;
@Dao
public interface StickerLispelDAO {
    @Insert
    void insert(StickerLispel stickerLispel);
    @Query("SELECT * FROM sticker_table_first")
    LiveData<List<StickerLispel>> getAllStickers();
    @Query(("DELETE FROM sticker_table_first"))
    void deleteAll();
    @Query("SELECT * FROM sticker_table_first WHERE number LIKE :number")
    LiveData<StickerLispel> getStickerByNumber(String number);
    @Query("SELECT * FROM sticker_table_first WHERE id = :id")
    LiveData<StickerLispel> getStickerById(long id);
}
