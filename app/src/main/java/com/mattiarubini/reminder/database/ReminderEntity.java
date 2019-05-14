package com.mattiarubini.reminder.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = CategoryReminderEntity.class,
                                    parentColumns = "name",
                                    childColumns = "category"))
public class ReminderEntity {

    // unsigned int
    @PrimaryKey
    @NonNull
    public int uid;

    @NonNull
    public String text;

    @NonNull
    public String trigger;

    @ColumnInfo(name = "category")
    public String category;

}
