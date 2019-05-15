package com.mattiarubini.reminder.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

// TODO: exportSchema to true https://stackoverflow.com/questions/44322178/room-schema-export-directory-is-not-provided-to-the-annotation-processor-so-we
@Database(entities = {CategoryReminderEntity.class, ReminderEntity.class}, version = 6, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CategoryReminderDao getCategoryReminderDao();

    public abstract ReminderDao getReminderDao();

}
