package com.mattiarubini.reminder.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {CategoryReminderEntity.class, ReminderEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CategoryReminderDao getCategoryReminderDao();

    public abstract ReminderDao getReminderDao();

}
