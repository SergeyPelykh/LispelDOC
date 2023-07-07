package com.lispel.lispeldoc.secondVersion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lispel.lispeldoc.secondVersion.model.Cartridge;
import com.lispel.lispeldoc.secondVersion.model.Sticker;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CartridgeDAO {
    @Insert
    void insert(Cartridge cartridge);

    @Update
    void update(Cartridge cartridge);

    @Query("SELECT * FROM cartridge_table_second")
    LiveData<List<Cartridge>> getAllCartridges();

    @Query("SELECT * FROM cartridge_table_second WHERE model LIKE :model")
    LiveData<Cartridge> getCartridgeByModel(String model);

    @Query("UPDATE cartridge_table_second SET stickers = :stickers WHERE id = :id")
    void updateStickers(String stickers, Long id);

    @Query(("DELETE FROM cartridge_table_second"))
    void deleteAll();

    @Query("SELECT * FROM cartridge_table_second WHERE id = :id")
    LiveData<Cartridge> getCartridgeById(long id);

}