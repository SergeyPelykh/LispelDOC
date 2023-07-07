package com.lispel.lispeldoc.secondVersion.repositoriy;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.model.utility.Convert;
import com.lispel.lispeldoc.newVersion.repositories.LispelDataBase;
import com.lispel.lispeldoc.secondVersion.dao.CartridgeDAO;
import com.lispel.lispeldoc.secondVersion.dao.StickerDAO;
import com.lispel.lispeldoc.secondVersion.model.Cartridge;
import com.lispel.lispeldoc.secondVersion.model.Sticker;

import java.util.ArrayList;
import java.util.List;

public class CartridgeRepository {
    private CartridgeDAO cartridgeDAO;

    public CartridgeRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.cartridgeDAO = lispelDataBase.cartridgeDAOSecond();
    }
    public void insert(Cartridge cartridge){
        System.out.println("from insert()");
        LispelDataBase.databaseWriteExecutor.execute(()->{
            cartridgeDAO.insert(cartridge);
        });
    }
    public void updateStickers(Cartridge cartridge){
        System.out.println("from updateStickers()");
        LispelDataBase.databaseWriteExecutor.execute(()->{
            cartridgeDAO.updateStickers(Convert.listOfLongToString(cartridge.getStickers()), cartridge.getId());
        });
    }
    public void update(Cartridge cartridge){
        System.out.println("from update()");
        LispelDataBase.databaseWriteExecutor.execute(()->{
            cartridgeDAO.update(cartridge);
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
    public LiveData<Cartridge> getCartridgeById(long id){
        return getCartridgeById(id);
    }
    public LiveData<Cartridge> getCartridgeByModel(String model){
        System.out.println("from getCartridgeByModel()");
        return cartridgeDAO.getCartridgeByModel(model);
    }
}
