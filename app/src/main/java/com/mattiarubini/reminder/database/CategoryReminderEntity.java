package com.mattiarubini.reminder.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class CategoryReminderEntity {

    @PrimaryKey
    @ColumnInfo(name = "name")
    @NonNull
    public String name;



}
