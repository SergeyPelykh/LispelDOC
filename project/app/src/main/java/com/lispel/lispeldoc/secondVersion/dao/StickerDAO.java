package com.lispel.lispeldoc.secondVersion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.secondVersion.model.Sticker;

import java.util.List;
@Dao
public interface StickerDAO {
    @Insert
    Long insert(Sticker sticker);

    @Query("SELECT * FROM sticker_table")
    LiveData<List<Sticker>> getAllStickers();

    @Query("SELECT * FROM sticker_table WHERE number LIKE :number")
    LiveData<Sticker> getStickerByNumber(String number);

    @Query("SELECT * FROM sticker_table WHERE id = :id")
    LiveData<Sticker> getStickerById(long id);

    @Query(("DELETE FROM sticker_table"))
    void deleteAll();
}
