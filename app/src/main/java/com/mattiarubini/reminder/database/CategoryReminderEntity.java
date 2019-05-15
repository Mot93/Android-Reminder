package com.mattiarubini.reminder.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = {@Index("name")})
public class CategoryReminderEntity {

    @PrimaryKey
    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    public CategoryReminderEntity(String name){
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
