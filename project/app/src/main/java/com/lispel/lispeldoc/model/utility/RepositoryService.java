package com.lispel.lispeldoc.model.utility;

import androidx.lifecycle.LiveData;

import com.lispel.lispeldoc.secondVersion.model.Client;

import java.util.ArrayList;
import java.util.List;

public interface RepositoryService {
    LiveData<List<String>> findAllByStringField(String field);
    void insert(Client sticker);
    boolean insertNewEntityInBase(ArrayList<String> fields);
}
