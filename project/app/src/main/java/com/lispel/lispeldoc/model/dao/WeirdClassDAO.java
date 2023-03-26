package com.lispel.lispeldoc.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lispel.lispeldoc.model.lispel.WeirdClass;

import java.util.List;



@Dao
public interface WeirdClassDAO {
    @Delete
    void delete(WeirdClass weirdClass);
    @Insert()
    void insert (WeirdClass weirdClass);

    @Query("SELECT * FROM weird_table")
    LiveData<List<WeirdClass>> getAllWeirdClasses();
    @Query("DELETE FROM weird_table WHERE id = :id")
    void deleteById(int id);

    @Query("DELETE FROM weird_table")
    void deleteAll();

    @Query("SELECT * FROM weird_table WHERE number LIKE :number")
    LiveData<WeirdClass> getWeirdClass(String number);

    @Query("SELECT * FROM weird_table WHERE id = :id")
    LiveData<WeirdClass> getWeirdClassById(int id);

    @Update
    void update(WeirdClass weirdClass);
}
