package com.lispel.lispeldoc.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.model.lispel.StickerNumber;

import java.util.List;

@Dao
public interface StickerNumberDAO {
    @Insert()
    void insert (StickerNumber stickerNumber);

    @Query("SELECT * FROM sticker_number")
    LiveData<List<StickerNumber>> getAllStickerNumbers();

    @Query("DELETE FROM sticker_number")
    void deleteAll();
}
