package com.lispel.lispeldoc.model.lispel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lispel.lispeldoc.model.dao.StickerNumberDAO;
import com.lispel.lispeldoc.model.utility.Convert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {StickerNumber.class}, version = 1, exportSchema = false)
@TypeConverters({Convert.class})
public abstract class StickerNumberDatabase extends RoomDatabase {
    public abstract StickerNumberDAO stickerNumberDAO();
    private static volatile StickerNumberDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static StickerNumberDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StickerNumberDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StickerNumberDatabase.class, "sticker_number").build();
                }
            }
        }
        return INSTANCE;
    }
}

