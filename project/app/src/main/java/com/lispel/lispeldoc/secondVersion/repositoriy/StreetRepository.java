package com.lispel.lispeldoc.secondVersion.repositoriy;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.model.utility.RepositoryService;
import com.lispel.lispeldoc.newVersion.repositories.LispelDataBase;
import com.lispel.lispeldoc.secondVersion.dao.StreetDAO;
import com.lispel.lispeldoc.secondVersion.inteface.GetListOfFields;
import com.lispel.lispeldoc.secondVersion.model.Street;

import java.util.ArrayList;
import java.util.List;

public class StreetRepository implements RepositoryService {
    private String title;
    private StreetDAO streetDAO;

    public StreetRepository(Application application, String title){
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.streetDAO = lispelDataBase.streetDAO();
        this.title = title;
    }

    public void insert(Street street){
        LispelDataBase.databaseWriteExecutor.execute(() -> {
            streetDAO.insert(street);
        });
    }

    public LiveData<List<Street>> getAllStreets(){
        return streetDAO.getAllStreets(title);
    }

    public LiveData<List<Street>> getAllStreetsByName(String name){
        return streetDAO.getAllStreetsByName(title, name);
    }
    public LiveData<Street> getStreetByName(String name){
        return streetDAO.getStreetByName(title, name);
    }


    @Override
    public LiveData<List<String>> findAllByStringField(String field) {
        return streetDAO.getNamesByName(title,"%" + field + "%");
    }

    @Override
    public LiveData<? extends GetListOfFields> findByStringField(String field) {
        return null;
    }

    @Override
    public Long insert(GetListOfFields getListOfFields) {
        return null;
    }

    @Override
    public Long insertNewEntityInBase(ArrayList<String> fields) {
        return null;
    }

    @Override
    public ArrayList<String> getListOfFields() {
        return null;
    }
}
