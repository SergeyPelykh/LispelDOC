package com.lispel.lispeldoc.secondVersion.repositoriy;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.model.utility.RepositoryService;
import com.lispel.lispeldoc.newVersion.repositories.LispelDataBase;
import com.lispel.lispeldoc.secondVersion.dao.ClientDAO;
import com.lispel.lispeldoc.secondVersion.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements RepositoryService {
    private ClientDAO clientDAO;

    public ClientRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.clientDAO = lispelDataBase.ClientDAO();
    }
    public void insert(Client client){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            clientDAO.insert(client);
        });
    }

    @Override
    public boolean insertNewEntityInBase(ArrayList<String> fields) {
        Client client = new Client();
        client.setName(fields.get(0));
        client.setFullName(fields.get(1));
        client.setAddress(fields.get(2));
        client.setPhone(fields.get(3));
        client.setType(fields.get(4));
        insert(client);
        return true;
    }

    public LiveData<List<Client>> getAllClients(){
        return clientDAO.getAllClients();
    }
    public LiveData<List<Client>> getAllClientByName(String name){
        return clientDAO.getAllClientByName(name);
    }
    public void deleteAll(){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            clientDAO.deleteAll();
        });
    }
    public LiveData<Client> getClientById(long id){
        return clientDAO.getClientById(id);
    }
    public LiveData<Client> getClientByName(String name){
        return clientDAO.getClientByName(name);
    }

    @Override
    public LiveData<List<String>> findAllByStringField(String field) {
        return clientDAO.getNamesByName("%" + field + "%");
    }
}
