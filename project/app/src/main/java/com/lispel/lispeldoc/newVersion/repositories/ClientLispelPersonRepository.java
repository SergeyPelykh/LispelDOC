package com.lispel.lispeldoc.newVersion.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.newVersion.DAO.ClientLispelPersonDAO;
import com.lispel.lispeldoc.newVersion.models.ClientLispelPerson;

import java.util.List;

public class ClientLispelPersonRepository {

    private ClientLispelPersonDAO clientLispelPersonDAO;

    public ClientLispelPersonRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.clientLispelPersonDAO = lispelDataBase.clientLispelPersonDAO();
    }
    public void insert(ClientLispelPerson clientLispelPerson){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            clientLispelPersonDAO.insert(clientLispelPerson);
        });
    }
    public LiveData<List<ClientLispelPerson>> getAllClients(){
        return clientLispelPersonDAO.getAllClientLispelPersons();
    }
    public void deleteAll(){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            clientLispelPersonDAO.deleteAll();
        });
    }
    public LiveData<ClientLispelPerson>getClientByName(String name){
        return clientLispelPersonDAO.getClientByName(name);
    }
    public LiveData<ClientLispelPerson>getClientById(long id){
        return clientLispelPersonDAO.getClientById(id);
    }
}
