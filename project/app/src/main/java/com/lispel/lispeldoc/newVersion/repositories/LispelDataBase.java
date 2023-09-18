package com.lispel.lispeldoc.newVersion.repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.lispel.lispeldoc.newVersion.DAO.ClientLispelPersonDAO;
import com.lispel.lispeldoc.newVersion.DAO.StickerLispelDAO;
import com.lispel.lispeldoc.newVersion.DAO.TonerDAO;
import com.lispel.lispeldoc.newVersion.models.Cartridge;
import com.lispel.lispeldoc.newVersion.models.ClientLispelPerson;
import com.lispel.lispeldoc.newVersion.models.StickerLispel;
import com.lispel.lispeldoc.newVersion.models.Toner;
import com.lispel.lispeldoc.secondVersion.dao.CartridgeDAO;
import com.lispel.lispeldoc.secondVersion.dao.CartridgeSpecificDAO;
import com.lispel.lispeldoc.secondVersion.dao.ClientDAO;
import com.lispel.lispeldoc.secondVersion.dao.ComponentDAO;
import com.lispel.lispeldoc.secondVersion.dao.FieldDAO;
import com.lispel.lispeldoc.secondVersion.dao.StickerDAO;
import com.lispel.lispeldoc.secondVersion.dao.StreetDAO;
import com.lispel.lispeldoc.secondVersion.model.CartridgeSpecific;
import com.lispel.lispeldoc.secondVersion.model.Client;
import com.lispel.lispeldoc.secondVersion.model.Component;
import com.lispel.lispeldoc.secondVersion.model.Sticker;
import com.lispel.lispeldoc.secondVersion.model.Street;
import com.lispel.lispeldoc.secondVersion.uiServices.Field;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        com.lispel.lispeldoc.secondVersion.model.Cartridge.class,
        Component.class,
        Client.class,
        Sticker.class,
        Field.class,
        com.lispel.lispeldoc.secondVersion.model.Toner.class,
        Toner.class,
        Street.class,
        StickerLispel.class,
        ClientLispelPerson.class,
        Cartridge.class,
        CartridgeSpecific.class},
        version = 4, exportSchema = false)
public abstract class LispelDataBase extends RoomDatabase {
    public abstract CartridgeSpecificDAO cartridgeSpecificDAO();
    public abstract StreetDAO streetDAO();
    public abstract TonerDAO tonerDAO();
    public abstract ClientDAO ClientDAO();
    public abstract FieldDAO fieldDAO();
    public abstract ComponentDAO componentDAO();
    public abstract com.lispel.lispeldoc.secondVersion.dao.TonerDAO tonerDAOSecond();
    public abstract StickerDAO stickerDAO();
    //public abstract com.lispel.lispeldoc.secondVersion.dao.CartridgeDAO cartridgeDAOSecond();
    public abstract StickerLispelDAO stickerLispelDAO();
    public abstract ClientLispelPersonDAO clientLispelPersonDAO();
    public abstract CartridgeDAO cartridgeDAO();
    private static volatile LispelDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LispelDataBase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (LispelDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LispelDataBase.class, "lispel_database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}
