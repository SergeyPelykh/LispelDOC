package com.lispel.lispeldoc.newVersion.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.newVersion.DAO.TonerDAO;
import com.lispel.lispeldoc.newVersion.models.Toner;

import java.util.List;

public class TonerRepository {
    private TonerDAO tonerDAO;

    public TonerRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.tonerDAO = lispelDataBase.tonerDAO();
    }
    public void insert(Toner toner){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            tonerDAO.insert(toner);
        });
    }
    public LiveData<List<Toner>> getAllToners(){
        return tonerDAO.getAllToners();
    }
    public void deleteAll(){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            tonerDAO.deleteAll();
        });
    }
}
