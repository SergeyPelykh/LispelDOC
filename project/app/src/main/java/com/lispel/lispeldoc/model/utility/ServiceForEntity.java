package com.lispel.lispeldoc.model.utility;

import java.util.ArrayList;
import java.util.Map;

public class ServiceForEntity {
    public static ArrayList<String> MapToArrayString(Map<String, Integer> map){
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String , Integer> entry : map.entrySet()) {
            result.add(entry.getKey());
            result.add(entry.getValue().toString());
        }
        return result;
    }
}
