package com.lispel.lispeldoc.model.lispel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.model.dao.WeirdClassDAO;

import java.util.List;

public class WeirdClassRepository {
    private WeirdClassDAO weirdClassDAO;
    private LiveData<List<WeirdClass>> allWeirdClasses;

    public WeirdClassRepository(Application application) {
        WeirdClassDatabase weirdClassDatabase = WeirdClassDatabase.getDatabase(application);
        weirdClassDAO = weirdClassDatabase.weirdClassDAO();
        allWeirdClasses = weirdClassDAO.getAllWeirdClasses();
    }

    public LiveData<List<WeirdClass>> getAllWeirdClasses() {
        return allWeirdClasses;
    }
    void insert(WeirdClass weirdClass){
        WeirdClassDatabase.databaseWriteExecutor.execute(() ->{
            weirdClassDAO.insert(weirdClass);
        });
    }
    void deleteAll(){
        WeirdClassDatabase.databaseWriteExecutor.execute(() ->{
            weirdClassDAO.deleteAll();
        });
    }
}
