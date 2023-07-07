package com.lispel.lispeldoc.newVersion.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.newVersion.models.Cartridge;

import java.util.List;

@Dao
public interface CartridgeDAO {
    @Insert
    void insert(Cartridge cartridge);
    @Query("SELECT * FROM cartridge_table")
    LiveData<List<Cartridge>> getAllCartridges();
    @Query(("DELETE FROM cartridge_table"))
    void deleteAll();
}
