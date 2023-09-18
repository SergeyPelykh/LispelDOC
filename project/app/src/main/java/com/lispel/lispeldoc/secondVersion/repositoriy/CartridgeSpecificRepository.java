package com.lispel.lispeldoc.secondVersion.repositoriy;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.model.utility.RepositoryService;
import com.lispel.lispeldoc.newVersion.repositories.LispelDataBase;
import com.lispel.lispeldoc.secondVersion.dao.CartridgeSpecificDAO;
import com.lispel.lispeldoc.secondVersion.inteface.GetListOfFields;
import com.lispel.lispeldoc.secondVersion.model.CartridgeSpecific;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartridgeSpecificRepository implements RepositoryService {
    private CartridgeSpecificDAO cartridgeSpecificDAO;

    public CartridgeSpecificRepository(Application application){
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.cartridgeSpecificDAO = lispelDataBase.cartridgeSpecificDAO();
    }
    public Long insert(CartridgeSpecific cartridgeSpecific){
        return cartridgeSpecificDAO.insert(cartridgeSpecific);
    }

    @Override
    public LiveData<List<String>> findAllByStringField(String field) {
        return cartridgeSpecificDAO.getNamesByName("%" + field + "%");
    }

    @Override
    public LiveData<? extends GetListOfFields> findByStringField(String field) {
        return cartridgeSpecificDAO.getCartridgeSpecificByName(field);
    }

    @Override
    public Long insert(GetListOfFields getListOfFields) {
        return insert((CartridgeSpecific) getListOfFields);
    }

    @Override
    public Long insertNewEntityInBase(ArrayList<String> fields) {
        CartridgeSpecific cartridgeSpecific = new CartridgeSpecific();
        cartridgeSpecific.setModel(fields.get(0));
        cartridgeSpecific.setVendor(fields.get(1));
        if (fields.get(2).equals("оригинальный")) {
            cartridgeSpecific.setOriginality(true);
        }else {
            cartridgeSpecific.setOriginality(false);
        }
        return insert(cartridgeSpecific);
    }

    @Override
    public ArrayList<String> getListOfFields() {
        return new ArrayList<>(Arrays.asList( "vendor", "cartridgeModel","originality"));
    }
}
