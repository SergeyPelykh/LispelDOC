package com.lispel.lispeldoc.model.lispel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WeirdClassModel extends AndroidViewModel {
    private WeirdClassRepository weirdClassRepository;
    private final LiveData<List<WeirdClass>> allWeirdClasses;
    public WeirdClassModel(@NonNull Application application) {
        super(application);
        weirdClassRepository = new WeirdClassRepository(application);
        allWeirdClasses = weirdClassRepository.getAllWeirdClasses();
    }

    public LiveData<List<WeirdClass>> getAllWeirdClasses() {
        return allWeirdClasses;
    }

    public void insert(WeirdClass weirdClass){
        weirdClassRepository.insert(weirdClass);
    }
    public void deleteAll(){
        weirdClassRepository.deleteAll();
    }
}
