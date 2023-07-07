package com.lispel.lispeldoc.secondVersion.repositoriy;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.newVersion.repositories.LispelDataBase;
import com.lispel.lispeldoc.secondVersion.dao.ComponentDAO;
import com.lispel.lispeldoc.secondVersion.model.Component;
import java.util.List;

public class ComponentRepository {
    private ComponentDAO componentDAO;

    public ComponentRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.componentDAO = lispelDataBase.componentDAO();
    }
    public void insert(Component component){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            componentDAO.insert(component);
        });
    }
    public LiveData<List<Component>> getAllComponents(){
        return componentDAO.getAllComponents();
    }
    public void deleteAll(){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            componentDAO.deleteAll();
        });
    }
    public LiveData<Component> getComponentById(long id){
        return componentDAO.getComponentById(id);
    }
    public LiveData<Component> getComponentByName(String name){
        return componentDAO.getComponentByName(name);
    }
}
