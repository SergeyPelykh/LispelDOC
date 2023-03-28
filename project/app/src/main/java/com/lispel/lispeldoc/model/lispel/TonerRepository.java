package com.lispel.lispeldoc.model.lispel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.model.dao.TonerDAO;

import java.util.List;

public class TonerRepository {
    private TonerDAO tonerDAO;

    public TonerRepository(Application application) {
        WeirdClassDatabase weirdClassDatabase = WeirdClassDatabase.getDatabase(application);
        this.tonerDAO = weirdClassDatabase.tonerDAO();
    }
    public void insert(Toner toner){
        WeirdClassDatabase.databaseWriteExecutor.execute(()->{
            tonerDAO.insert(toner);
        });

    }
    public LiveData<List<Toner>> getAllToners(){
        return tonerDAO.getAllToners();
    }
    public void deleteAll(){
        WeirdClassDatabase.databaseWriteExecutor.execute(() ->{
            tonerDAO.deleteAll();
        });
    }
}
