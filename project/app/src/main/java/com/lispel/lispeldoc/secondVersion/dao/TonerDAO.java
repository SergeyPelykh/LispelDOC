package com.lispel.lispeldoc.secondVersion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.secondVersion.model.Sticker;
import com.lispel.lispeldoc.secondVersion.model.Toner;

import java.util.List;

@Dao
public interface TonerDAO {
    @Insert
    void insert(Toner toner);

    @Query("SELECT * FROM toner_table_second")
    LiveData<List<Toner>> getAllToners();

    @Query("SELECT * FROM toner_table_second WHERE name LIKE :name")
    LiveData<Toner> getTonerByName(String name);

    @Query("SELECT * FROM toner_table_second WHERE id = :id")
    LiveData<Toner> getTonerById(long id);

    @Query(("DELETE FROM toner_table_second"))
    void deleteAll();
}
