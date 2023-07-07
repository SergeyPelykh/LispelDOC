package com.lispel.lispeldoc.secondVersion.repositoriy;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.newVersion.repositories.LispelDataBase;
import com.lispel.lispeldoc.secondVersion.dao.StickerDAO;
import com.lispel.lispeldoc.secondVersion.dao.TonerDAO;
import com.lispel.lispeldoc.secondVersion.model.Sticker;
import com.lispel.lispeldoc.secondVersion.model.Toner;

import java.util.List;

public class TonerRepository {
    private TonerDAO tonerDAO;

    public TonerRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.tonerDAO = lispelDataBase.tonerDAOSecond();
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
    public LiveData<Toner> getTonerById(long id){
        return tonerDAO.getTonerById(id);
    }
    public LiveData<Toner> getTonerByName(String name){
        return tonerDAO.getTonerByName(name);
    }
}
