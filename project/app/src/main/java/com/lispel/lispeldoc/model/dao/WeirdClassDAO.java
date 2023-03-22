package com.lispel.lispeldoc.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.lispel.lispeldoc.model.lispel.WeirdClass;

import java.util.List;

@Dao
public interface WeirdClassDAO {
    @Insert()
    void insert (WeirdClass weirdClass);

    @Query("SELECT * FROM weird_table")
    LiveData<List<WeirdClass>> getAllWeirdClasses();

    @Query("DELETE FROM weird_table")
    void deleteAll();
}
