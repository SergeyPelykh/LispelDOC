package com.lispel.lispeldoc.secondVersion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.secondVersion.model.Street;

import java.util.List;

@Dao
public interface StreetDAO {
    @Insert
    Long insert(Street street);

    @Query("SELECT * FROM street_table WHERE title = :title")
    LiveData<List<Street>> getAllStreets(String title);

    @Query("SELECT * FROM street_table WHERE name LIKE :name AND title = :title")
    LiveData<List<Street>> getAllStreetsByName(String title, String name);

    @Query("SELECT * FROM street_table WHERE name LIKE :name AND title = :title")
    LiveData<Street> getStreetByName(String title, String name);

    @Query("SELECT * FROM street_table WHERE id = :id AND title = :title")
    LiveData<Street> getStreetById(String title, Long id);

    @Query("SELECT name FROM street_table WHERE name LIKE :name AND title = :title")
    LiveData<List<String>>getNamesByName(String title, String name);
}
