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
    Long insert(Cartridge cartridge);

    @Update
    void update(Cartridge cartridge);

    @Query("SELECT * FROM cartridge_table_second")
    LiveData<List<Cartridge>> getAllCartridges();

    @Query("SELECT * FROM cartridge_table_second WHERE model LIKE :model")
    LiveData<Cartridge> getCartridgeByModel(String model);

    @Query("SELECT * FROM cartridge_table_second WHERE owner = :owner")
    LiveData<Cartridge> getCartridgeByOwner(Long owner);

    @Query("UPDATE cartridge_table_second SET stickers = :stickers WHERE id = :id")
    void updateStickers(String stickers, Long id);

    @Query(("DELETE FROM cartridge_table_second"))
    void deleteAll();

    @Query("SELECT * FROM cartridge_table_second WHERE id = :id")
    LiveData<Cartridge> getCartridgeById(long id);
    @Query("SELECT * FROM cartridge_table_second WHERE stickers = :sticker")
    LiveData<Cartridge> getCartridgeBySticker(String sticker);

    @Query("SELECT model FROM cartridge_table_second WHERE model LIKE :model")
    LiveData<List<String>>getNamesByName(String model);
}
