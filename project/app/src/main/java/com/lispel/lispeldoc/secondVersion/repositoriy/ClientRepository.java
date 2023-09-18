package com.lispel.lispeldoc.secondVersion.repositoriy;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.model.utility.RepositoryService;
import com.lispel.lispeldoc.newVersion.repositories.LispelDataBase;
import com.lispel.lispeldoc.secondVersion.dao.ClientDAO;
import com.lispel.lispeldoc.secondVersion.inteface.GetListOfFields;
import com.lispel.lispeldoc.secondVersion.model.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientRepository implements RepositoryService {
    private ClientDAO clientDAO;

    public ClientRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.clientDAO = lispelDataBase.ClientDAO();
    }
    public Long insert(Client client){
            return clientDAO.insert(client);
    }

    @Override
    public Long insertNewEntityInBase(ArrayList<String> fields) {
        Client client = new Client();
        client.setName(fields.get(0));
        client.setFullName(fields.get(1));
        client.setAddress(fields.get(2));
        client.setPhone(fields.get(3));
        client.setType(fields.get(4));

        return insert(client);
    }

    @Override
    public ArrayList<String> getListOfFields() {
        return new ArrayList<>(Arrays.asList("name", "fullName",
                "address", "phone", "clientType", "cartridge"));
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

    @Override
    public LiveData<Client> findByStringField(String field) {
        return clientDAO.getClientByName(field);
    }

    @Override
    public Long insert(GetListOfFields getListOfFields) {

        return insert((Client) getListOfFields);
    }

    public Long insertNewEntity(ArrayList<String> properties){
        if (getListOfFields().size() == properties.size()) {
            Client client = new Client();
            client.setName(properties.get(0));
            client.setFullName(properties.get(1));
            client.setAddress(properties.get(2));
            client.setPhone(properties.get(3));
            client.setType(properties.get(4));
            return insert(client);
        }else {
            throw new IndexOutOfBoundsException("size of Array with properties not equal to new Entity");
        }
    }
}
