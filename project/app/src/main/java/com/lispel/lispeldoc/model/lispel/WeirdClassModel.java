package com.lispel.lispeldoc.model.lispel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class WeirdClassModel extends AndroidViewModel {
    private WeirdClassRepository weirdClassRepository;
    private final LiveData<List<WeirdClass>> allWeirdClasses;
    private LiveData<List<Integer>> idByNumber;
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
    public void delete(WeirdClass weirdClass){
        weirdClassRepository.delete(weirdClass);
    }
    public LiveData<WeirdClass> getById(int id){
        return weirdClassRepository.getById(id);
    }

    public void deleteById(int id){
        weirdClassRepository.deleteById(id);
    }
    public void update(WeirdClass weirdClass){weirdClassRepository.update(weirdClass);}

}
