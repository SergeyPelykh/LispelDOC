package com.lispel.lispeldoc.model.lispel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lispel.lispeldoc.model.dao.StickerNumberDAO;
import com.lispel.lispeldoc.model.dao.TonerDAO;
import com.lispel.lispeldoc.model.dao.WeirdClassDAO;
import com.lispel.lispeldoc.model.utility.Convert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {WeirdClass.class, Toner.class}, version = 3, exportSchema = false)
@TypeConverters({Convert.class})
public abstract class WeirdClassDatabase extends RoomDatabase {
    public abstract WeirdClassDAO weirdClassDAO();
    public abstract TonerDAO tonerDAO();
    private static volatile WeirdClassDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static WeirdClassDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StickerNumberDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WeirdClassDatabase.class, "weird_table").addMigrations(WeirdClassDatabase.MIGRATION_2_3).build();
                }
            }
        }
        return INSTANCE;
    }

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE weird_table ADD COLUMN date_of_last_edit INTEGER DEFAULT NULL");
        }
    };
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `toner_table` (`id` INTEGER NOT NULL, `name` TEXT, `fullName` TEXT, PRIMARY KEY(`id`))");
        }
    };
}
