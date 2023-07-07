package com.lispel.lispeldoc.newVersion.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.newVersion.DAO.StickerLispelDAO;
import com.lispel.lispeldoc.newVersion.DAO.TonerDAO;
import com.lispel.lispeldoc.newVersion.models.StickerLispel;
import com.lispel.lispeldoc.newVersion.models.Toner;

import java.util.List;

public class StickerLispelRepository {
    private StickerLispelDAO stickerLispelDAO;

    public StickerLispelRepository(Application application) {
        LispelDataBase lispelDataBase = LispelDataBase.getDatabase(application);
        this.stickerLispelDAO = lispelDataBase.stickerLispelDAO();
    }
    public void insert(StickerLispel stickerLispel){
        LispelDataBase.databaseWriteExecutor.execute(()->{
             stickerLispelDAO.insert(stickerLispel);
        });
    }
    public LiveData<List<StickerLispel>> getAllSticker(){
        return stickerLispelDAO.getAllStickers();
    }
    public void deleteAll(){
        LispelDataBase.databaseWriteExecutor.execute(()->{
            stickerLispelDAO.deleteAll();
        });
    }
    public LiveData<StickerLispel> getStickerById(long id){
        return stickerLispelDAO.getStickerById(id);
    }
    public LiveData<StickerLispel> getStickerByNumber(String number){
        return stickerLispelDAO.getStickerByNumber(number);
    }

}
