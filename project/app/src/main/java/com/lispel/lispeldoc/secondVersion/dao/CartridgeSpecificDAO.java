package com.lispel.lispeldoc.secondVersion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.secondVersion.model.CartridgeSpecific;

import java.util.List;

@Dao
public interface CartridgeSpecificDAO {
    @Insert
    Long insert(CartridgeSpecific cartridgeSpecific);

    @Query("SELECT * FROM cartridge_specific_table")
    LiveData<List<CartridgeSpecific>> getAllCartridgeSpecifics();

    @Query("SELECT * FROM cartridge_specific_table WHERE model LIKE :model")
    LiveData<CartridgeSpecific> getCartridgeSpecificByName(String model);

    @Query("SELECT * FROM cartridge_specific_table WHERE model LIKE :model")
    LiveData<List<CartridgeSpecific>> getAllCartridgeSpecificByName(String model);

    @Query("SELECT * FROM cartridge_specific_table WHERE id = :id")
    LiveData<CartridgeSpecific> getCartridgeSpecificById(long id);

    @Query(("DELETE FROM cartridge_specific_table"))
    void deleteAll();
    @Query("SELECT model FROM cartridge_specific_table WHERE model LIKE :model")
    LiveData<List<String>>getNamesByName(String model);
}
