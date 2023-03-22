package com.lispel.lispeldoc.model.lispel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StickerNumberModel extends AndroidViewModel {
    private StickerNumberRepository stickerNumberRepository;
    private final LiveData<List<StickerNumber>> allStickerNumbers;
    public StickerNumberModel(@NonNull Application application) {
        super(application);
        stickerNumberRepository = new StickerNumberRepository(application);
        allStickerNumbers = stickerNumberRepository.getAllStickerNumbers();
    }

    public LiveData<List<StickerNumber>> getAllStickerNumbers() {
        return allStickerNumbers;
    }
    public void insert(StickerNumber stickerNumber){
        stickerNumberRepository.insert(stickerNumber);
    }
    public void deleteAll(){
        stickerNumberRepository.deleteAll();
    }
}
