package com.lispel.lispeldoc.secondVersion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.secondVersion.model.Component;
import com.lispel.lispeldoc.secondVersion.model.Sticker;

import java.util.List;

@Dao
public interface ComponentDAO {
    @Insert
    void insert(Component component);

    @Query("SELECT * FROM component_table")
    LiveData<List<Component>> getAllComponents();

    @Query("SELECT * FROM component_table WHERE name LIKE :name")
    LiveData<Component> getComponentByName(String name);

    @Query("SELECT * FROM component_table WHERE id = :id")
    LiveData<Component> getComponentById(long id);

    @Query(("DELETE FROM component_table"))
    void deleteAll();
}
