package com.lispel.lispeldoc.model.lispel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.lispel.lispeldoc.model.dao.StickerNumberDAO;
import com.lispel.lispeldoc.model.dao.WeirdClassDAO;
import com.lispel.lispeldoc.model.utility.Convert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {WeirdClass.class}, version = 1, exportSchema = false)
@TypeConverters({Convert.class})
public abstract class WeirdClassDatabase extends RoomDatabase {
    public abstract WeirdClassDAO weirdClassDAO();
    private static volatile WeirdClassDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WeirdClassDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StickerNumberDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WeirdClassDatabase.class, "weird_table").build();
                }
            }
        }
        return INSTANCE;
    }
}
