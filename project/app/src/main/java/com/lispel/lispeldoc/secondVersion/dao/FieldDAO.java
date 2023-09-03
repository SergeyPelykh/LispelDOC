package com.lispel.lispeldoc.secondVersion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.secondVersion.uiServices.Field;

import java.util.List;

@Dao
public interface FieldDAO {
    @Insert
    Long insert(Field field);

    @Query("SELECT * FROM field_table")
    LiveData<List<Field>> getAllFields();

    @Query("SELECT * FROM field_table WHERE name LIKE :name")
    LiveData<List<Field>> getAllFieldsByName(String name);

    @Query("SELECT * FROM field_table WHERE name LIKE :name")
    LiveData<Field> getFieldByName(String name);

    @Query("SELECT * FROM field_table WHERE id = :id")
    LiveData<Field> getFieldById(Long id);
}
