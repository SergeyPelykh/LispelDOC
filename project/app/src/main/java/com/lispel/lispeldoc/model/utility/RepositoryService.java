package com.lispel.lispeldoc.model.utility;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.secondVersion.inteface.GetListOfFields;

import java.util.ArrayList;
import java.util.List;

public interface RepositoryService {
    LiveData<List<String>> findAllByStringField(String field);
    LiveData<? extends GetListOfFields> findByStringField(String field);
    Long insert(GetListOfFields getListOfFields);
    Long insertNewEntityInBase(ArrayList<String> fields);
}
