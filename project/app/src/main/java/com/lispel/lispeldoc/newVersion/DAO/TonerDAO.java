package com.lispel.lispeldoc.newVersion.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.newVersion.models.Toner;

import java.util.List;

@Dao
public interface TonerDAO {
    @Insert
    void insert(Toner toner);
    @Query("SELECT * FROM toner_table")
    LiveData<List<Toner>> getAllToners();
    @Query(("DELETE FROM toner_table"))
    void deleteAll();
}
