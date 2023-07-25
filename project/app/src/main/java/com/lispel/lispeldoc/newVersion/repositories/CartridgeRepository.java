package com.lispel.lispeldoc.newVersion.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.model.utility.RepositoryService;
import com.lispel.lispeldoc.secondVersion.dao.CartridgeDAO;
import com.lispel.lispeldoc.secondVersion.inteface.GetListOfFields;
import com.lispel.lispeldoc.secondVersion.model.Cartridge;

import java.util.ArrayList;
import java.util.List;

public class CartridgeRepository  {
    private CartridgeDAO cartridgeDAO;

    public CartridgeRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.cartridgeDAO = lispelDataBase.cartridgeDAO();
    }
    public void insert(Cartridge cartridge){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            cartridgeDAO.insert(cartridge);
        });
    }
    public LiveData<List<Cartridge>> getAllCartridges(){
        return cartridgeDAO.getAllCartridges();
    }
    public void deleteAll(){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            cartridgeDAO.deleteAll();
        });
    }


//    @Override
//    public LiveData<List<String>> findAllByStringField(String field) {
//        return cartridgeDAO.getNamesByName("%" + field + "%");
//    }
//
//    @Override
//    public Long insert(GetListOfFields getListOfFields) {
//        insert((Cartridge) getListOfFields);
//        return null;
//    }
//
//    @Override
//    public boolean insertNewEntityInBase(ArrayList<String> fields) {
//        return false;
//    }
}
