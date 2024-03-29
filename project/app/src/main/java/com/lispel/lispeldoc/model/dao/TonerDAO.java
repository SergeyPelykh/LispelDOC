package com.lispel.lispeldoc.model.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.lispel.lispeldoc.model.models.Toner;

import java.util.List;

@Dao
public interface TonerDAO {
    @Insert()
    void insert(Toner toner);

    @Query("SELECT * FROM toner_table2")
    LiveData<List<Toner>> getAllToners();

    @Query("DELETE FROM toner_table2")
    void deleteAll();

}
