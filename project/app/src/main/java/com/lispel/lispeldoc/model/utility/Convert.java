package com.lispel.lispeldoc.model.utility;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.lispel.lispeldoc.model.abstracts.ClientType;

import java.util.Date;

@ProvidedTypeConverter


public class Convert {
        @TypeConverter
        public static Long dateToLong(Date date){
            return date == null ? null : date.getTime();
        }
        @TypeConverter
        public static Date fromTimestamp(Long time){
            return time == null ? null : new Date(time);
        }
        @TypeConverter
        public static String clientTypeToString(ClientType clientType){
            return clientType == null ? null : clientType.name();
        }
        @TypeConverter
        public static ClientType clientTypeFromString(String clientType){
            return clientType == null ? null : ClientType.valueOf(clientType);
        }
    }


