package com.lispel.lispeldoc.secondVersion.repositoriy;


import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lispel.lispeldoc.newVersion.repositories.LispelDataBase;
import com.lispel.lispeldoc.secondVersion.dao.StickerDAO;
import com.lispel.lispeldoc.secondVersion.model.Sticker;

import java.util.ArrayList;
import java.util.List;

public class StickerRepository {
    private StickerDAO stickerDAO;

    public StickerRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.stickerDAO = lispelDataBase.stickerDAO();
    }
    public Long insert(Sticker sticker){
        final Long[] res = new Long[1];
        LispelDataBase.databaseWriteExecutor.execute(()-> {
            res[0] = stickerDAO.insert(sticker);
        });
        while (true){
            if (res[0] != null){
                return res[0];
            }
        }

    }
    public LiveData<List<Sticker>> getAllSticker(){
        return stickerDAO.getAllStickers();
    }
    public void deleteAll(){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            stickerDAO.deleteAll();
        });
    }
    public LiveData<Sticker> getStickerById(long id){
        return stickerDAO.getStickerById(id);
    }
    public LiveData<Sticker> getStickerByNumber(String number){
        return stickerDAO.getStickerByNumber(number);
    }
    public LiveData<List<Sticker>> getStickersFromListIds(LifecycleOwner lifecycle, List<Long> list){
        System.out.println("from method getStickersFromListIds: " + list);
        MutableLiveData <List<Sticker>> liveData = new MutableLiveData<>();
        List<Sticker> list1 = new ArrayList<>();
        for (Long id: list) {
            System.out.println("from method getStickersFromListIds cycle: " + id);
            getStickerById(id).observe(lifecycle, x ->{
                System.out.println("get: " + x.getNumber());
                list1.add(x);
            });
        }
        while (true){
            if (list.size() == list1.size()){
                liveData.setValue(list1);
                return liveData;
            }
        }

    }
}
