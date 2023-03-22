package com.lispel.lispeldoc.model.lispel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.model.dao.StickerNumberDAO;

import java.util.List;

public class StickerNumberRepository {
    private StickerNumberDAO stickerNumberDAO;
    private LiveData<List<StickerNumber>> allStickerNumbers;

    StickerNumberRepository(Application application){
                StickerNumberDatabase stickerNumberDatabase = StickerNumberDatabase.getDatabase(application);
                stickerNumberDAO = stickerNumberDatabase.stickerNumberDAO();
                allStickerNumbers = stickerNumberDAO.getAllStickerNumbers();
    }
    LiveData<List<StickerNumber>> getAllStickerNumbers(){
        return allStickerNumbers;
    }

    void insert(StickerNumber stickerNumber){
        StickerNumberDatabase.databaseWriteExecutor.execute(() -> {
            stickerNumberDAO.insert(stickerNumber);
        });
    }
    void deleteAll(){
        StickerNumberDatabase.databaseWriteExecutor.execute(()->{
            stickerNumberDAO.deleteAll();
        });
    }
}
