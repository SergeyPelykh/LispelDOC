package com.lispel.lispeldoc.newVersion.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.newVersion.DAO.CartridgeDAO;
import com.lispel.lispeldoc.newVersion.models.Cartridge;

import java.util.List;

public class CartridgeRepository {
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
}
