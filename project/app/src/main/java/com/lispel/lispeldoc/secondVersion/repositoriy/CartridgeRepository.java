package com.lispel.lispeldoc.secondVersion.repositoriy;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.model.utility.Convert;
import com.lispel.lispeldoc.model.utility.RepositoryService;
import com.lispel.lispeldoc.newVersion.repositories.LispelDataBase;
import com.lispel.lispeldoc.secondVersion.dao.CartridgeDAO;
import com.lispel.lispeldoc.secondVersion.inteface.GetListOfFields;
import com.lispel.lispeldoc.secondVersion.model.Cartridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartridgeRepository implements RepositoryService{
    private CartridgeDAO cartridgeDAO;

    public Long insert(Cartridge cartridge){
        System.out.println("insert()");
        return cartridgeDAO.insert(cartridge);
    }

    public CartridgeRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.cartridgeDAO = lispelDataBase.cartridgeDAO();
    }
//    public void insert(Cartridge cartridge){
//        System.out.println("from insert()");
//        LispelDataBase.databaseWriteExecutor.execute(()->{
//            cartridgeDAO.insert(cartridge);
//        });
//    }
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
    public LiveData<Cartridge> getCartridgeByOwner(long id){
        return cartridgeDAO.getCartridgeByOwner(id);
    }
    public LiveData<Cartridge> getCartridgeByModel(String model){
        System.out.println("from getCartridgeByModel()");
        return cartridgeDAO.getCartridgeByModel(model);
    }

    @Override
    public LiveData<List<String>> findAllByStringField(String field) {
        return cartridgeDAO.getNamesByName("%" + field + "%");
    }

    @Override
    public LiveData<? extends GetListOfFields> findByStringField(String field) {
        return cartridgeDAO.getCartridgeBySticker("%" + field + "%");
    }

    @Override
    public Long insert(GetListOfFields getListOfFields) {
        return null;
    }

    @Override
    public Long insertNewEntityInBase(ArrayList<String> fields) {
        Cartridge cartridge = new Cartridge();
        cartridge.setModel(fields.get(0));
        cartridge.setVendor(fields.get(1));
        cartridge.setOriginality(Boolean.parseBoolean(fields.get(2)));
        cartridge.setOwner(Long.parseLong(fields.get(3)));
        ArrayList<Long> stickers = new ArrayList<>();
        stickers.add(Long.parseLong(fields.get(4)));
        System.out.println("insertNewEntityInBase()");
        //cartridge.setStickers("stickers");
        cartridge.addSticker(Long.parseLong(fields.get(4)));
        return cartridgeDAO.insert(cartridge);
    }
}
