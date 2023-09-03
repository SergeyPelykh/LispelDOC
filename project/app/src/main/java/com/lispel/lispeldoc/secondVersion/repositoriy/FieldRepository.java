package com.lispel.lispeldoc.secondVersion.repositoriy;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.newVersion.repositories.LispelDataBase;
import com.lispel.lispeldoc.secondVersion.dao.FieldDAO;
import com.lispel.lispeldoc.secondVersion.model.Street;
import com.lispel.lispeldoc.secondVersion.uiServices.Field;

import java.util.List;

public class FieldRepository {
    private FieldDAO fieldDAO;

    public FieldRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.fieldDAO = lispelDataBase.fieldDAO();
    }

    public void insert(Field field){
        LispelDataBase.databaseWriteExecutor.execute(() -> {
            //System.out.println("insert in base Field " + field.getName() + "-" + field.getInscription());
            fieldDAO.insert(field);
        });
    }

    public LiveData<List<Field>> getAllFields(){
        return fieldDAO.getAllFields();
    }

    public LiveData<List<Field>> getAllFieldsByName(String name){
        return fieldDAO.getAllFieldsByName(name);
    }
    public LiveData<Field> getFieldByName(String name){
        return fieldDAO.getFieldByName(name);
    }
}
