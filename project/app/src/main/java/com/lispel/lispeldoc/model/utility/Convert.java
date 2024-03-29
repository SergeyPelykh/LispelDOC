package com.lispel.lispeldoc.model.utility;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.lispel.lispeldoc.model.abstracts.ClientType;
import com.lispel.lispeldoc.newVersion.repositories.LispelDataBase;
import com.lispel.lispeldoc.secondVersion.model.Client;
import com.lispel.lispeldoc.secondVersion.repositoriy.ClientRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@ProvidedTypeConverter


public class Convert {


    @TypeConverter
    public static Long dateToLong(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date fromTimestamp(Long time) {
        return time == null ? null : new Date(time);
    }

    @TypeConverter
    public static String clientTypeToString(ClientType clientType) {
        return clientType == null ? null : clientType.name();
    }

    @TypeConverter
    public static ClientType clientTypeFromString(String clientType) {
        return clientType == null ? null : ClientType.valueOf(clientType);
    }

    @TypeConverter
    public static String listOfStringToString(List<String> list) {
        return list == null ? null : arrToString(list);
    }
    private static String arrToString(List<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        if (list.size() == 1){
            stringBuilder.append(list.get(0));
        } else {
            for (String x : list) {
                stringBuilder.append(x.toString() + "-");
            }
        }
        return stringBuilder.toString();
    }

    @TypeConverter
    public static String listOfLongToString(List<Long> list) {
        return list == null ? null : arrLongToString(list);
    }
    private static String arrLongToString(List<Long> list){
        StringBuilder stringBuilder = new StringBuilder();
        if (list.size() == 1){
            stringBuilder.append(list.get(0).toString());
        } else {
            for (Long x : list) {
                stringBuilder.append(x.toString() + "-");
            }
        }
        return stringBuilder.toString();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @TypeConverter
    public static List<String> StringToListOfString(String str){
        return str == null ? null : stringToArr(str);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static List<String> stringToArr(String str) {
        List<String> arr = new ArrayList<>();
        String[] splitStr = str.split("-");
        Stream<String> stream = Stream.of(splitStr);
        stream.forEach(x -> {
            arr.add(x);
        });
        return arr;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @TypeConverter
    public static ArrayList<Long> StringToListOfLong(String str){
        ArrayList<Long> arr = new ArrayList<>();
        if (!(str.equals(""))) {
            stringToArrLong(str, arr);
        }
        return arr;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static ArrayList<Long> stringToArrLong(String str, ArrayList<Long> arr) {
            for (String st: str.split("-")) {
                arr.add(Long.parseLong(st));
            }
        return arr;
    }

    @TypeConverter
    public static Long ClientToLong(Client client) {
        return client.getId();
    }

    @TypeConverter
    public static Client longToClient(Long id){
        return new Client(id);
    }
}


