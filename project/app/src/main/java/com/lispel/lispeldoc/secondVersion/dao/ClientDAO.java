package com.lispel.lispeldoc.secondVersion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.secondVersion.model.Client;
import com.lispel.lispeldoc.secondVersion.model.Sticker;

import java.util.List;
@Dao
public interface ClientDAO {
    @Insert
    void insert(Client sticker);

    @Query("SELECT * FROM client_table")
    LiveData<List<Client>> getAllClients();

    @Query("SELECT * FROM client_table WHERE name LIKE :name")
    LiveData<Client> getClientByName(String name);

    @Query("SELECT * FROM client_table WHERE name LIKE :name")
    LiveData<List<Client>> getAllClientByName(String name);

    @Query("SELECT * FROM client_table WHERE id = :id")
    LiveData<Client> getClientById(long id);

    @Query(("DELETE FROM client_table"))
    void deleteAll();
    @Query("SELECT name FROM client_table WHERE name LIKE :name")
    LiveData<List<String>>getNamesByName(String name);
}
