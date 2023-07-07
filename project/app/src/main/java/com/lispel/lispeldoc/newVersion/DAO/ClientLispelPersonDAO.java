package com.lispel.lispeldoc.newVersion.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lispel.lispeldoc.model.lispel.WeirdClass;
import com.lispel.lispeldoc.newVersion.models.ClientLispel;
import com.lispel.lispeldoc.newVersion.models.ClientLispelPerson;

import java.util.List;
@Dao
public interface ClientLispelPersonDAO {
    @Insert
    void insert(ClientLispelPerson clientLispelPerson);
    @Query("SELECT * FROM clientPerson_table")
    LiveData<List<ClientLispelPerson>> getAllClientLispelPersons();
    @Query(("DELETE FROM clientPerson_table"))
    void deleteAll();
    @Query("SELECT * FROM clientPerson_table WHERE name LIKE :name")
    LiveData<ClientLispelPerson> getClientByName(String name);

    @Query("SELECT * FROM clientPerson_table WHERE id = :id")
    LiveData<ClientLispelPerson> getClientById(long id);
}
