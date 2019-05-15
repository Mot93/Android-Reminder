package com.mattiarubini.reminder.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CategoryReminderDao {

    @Query("SELECT * FROM CategoryReminderEntity")
    List<CategoryReminderEntity> getAll();

    @Query("SELECT COUNT() FROM CategoryReminderEntity WHERE name = :category ")
    int categoryExist(String category);

    @Insert
    void addCategory(CategoryReminderEntity... categories);

    @Update
    void updateCategory(CategoryReminderEntity... categories);

    @Delete
    void deleteCategory(CategoryReminderEntity... categories);

}
